package heven.holt.expand.ktx.activityresult

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import heven.holt.expand.ktx.isPermissionGranted
import heven.holt.expand.ktx.topActivity

fun ActivityResultCaller.registerForRequestPermissionResult(
    onGranted: () -> Unit,
    onDenied: AppSettingsScope.() -> Unit,
    onShowRequestRationale: PermissionScope.() -> Unit
): ActivityResultLauncher<String> {
    var permissionLauncher: ActivityResultLauncher<String>? = null
    var permission: String? = null
    val launchAppSettingsLauncher = registerForLaunchAppSettingsResult {
        permissionLauncher?.launch(permission)
    }
    permissionLauncher = registerForActivityResult(RequestPermissionContract()) {
        permission = it.first
        when {
            it.second -> onGranted()
            !permission.isNullOrEmpty() && ActivityCompat.shouldShowRequestPermissionRationale(
                topActivity,
                permission!!
            ) ->
                onShowRequestRationale(PermissionScope { permissionLauncher?.launch(permission) })

            else -> onDenied(AppSettingsScope { launchAppSettingsLauncher.launch() })
        }
    }
    return permissionLauncher
}

fun ActivityResultCaller.registerForRequestPermissionResult(callback: ActivityResultCallback<Boolean>) =
    registerForActivityResult(ActivityResultContracts.RequestPermission(), callback)

class RequestPermissionContract : ActivityResultContract<String, Pair<String, Boolean>>() {
    private lateinit var permission: String

    override fun createIntent(context: Context, input: String) =
        Intent(ActivityResultContracts.RequestMultiplePermissions.ACTION_REQUEST_PERMISSIONS)
            .putExtra(
                ActivityResultContracts.RequestMultiplePermissions.EXTRA_PERMISSIONS,
                arrayOf(input)
            )
            .also { permission = input }

    override fun parseResult(resultCode: Int, intent: Intent?): Pair<String, Boolean> {
        if (intent == null || resultCode != Activity.RESULT_OK) return permission to false
        val grantResults =
            intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS")
        return permission to if (grantResults == null || grantResults.isEmpty()) false else grantResults[0] == PackageManager.PERMISSION_GRANTED
    }

    override fun getSynchronousResult(
        context: Context,
        input: String
    ): SynchronousResult<Pair<String, Boolean>>? =
        when {
            context.isPermissionGranted(input) -> SynchronousResult(input to true)
            else -> null
        }
}

fun interface PermissionScope {
    fun requestPermissionAgain()
}