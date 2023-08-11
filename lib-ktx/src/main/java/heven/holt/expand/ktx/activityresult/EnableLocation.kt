package heven.holt.expand.ktx.activityresult

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import heven.holt.expand.ktx.isLocationEnabled

fun ActivityResultCaller.registerForEnableLocationResult(
    onLocationEnabled: LocationScope.(Boolean) -> Unit
): ActivityResultLauncher<Unit> {
    var enableLocationLauncher: ActivityResultLauncher<Unit>? = null
    enableLocationLauncher = registerForActivityResult(EnableLocationContract()) {
        onLocationEnabled(LocationScope { enableLocationLauncher?.launch() }, it)
    }
    return enableLocationLauncher
}

class EnableLocationContract : ActivityResultContract<Unit, Boolean>() {
    override fun createIntent(context: Context, input: Unit): Intent =
        Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)

    override fun parseResult(resultCode: Int, intent: Intent?): Boolean = isLocationEnabled

    override fun getSynchronousResult(context: Context, input: Unit): SynchronousResult<Boolean>? {
        return if (isLocationEnabled) SynchronousResult(true) else null
    }
}

fun interface LocationScope {
    fun enableLocation()
}