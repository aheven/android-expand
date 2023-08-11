package heven.holt.expand.ktx.activityresult

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import heven.holt.expand.ktx.isBluetoothEnabled

fun ActivityResultCaller.registerForEnableBluetoothResult(
    onLocationDisabled: LocationScope.() -> Unit,
    onBluetoothEnabled: BluetoothScope.(Boolean) -> Unit
): ActivityResultLauncher<Unit> {
    val enableBluetoothLauncher = registerForEnableBluetoothResult(onBluetoothEnabled)
    val enableLocationLauncher = registerForEnableLocationResult { enable ->
        if (enable) {
            enableBluetoothLauncher.launch()
        } else {
            onLocationDisabled()
        }
    }
    return enableLocationLauncher
}

fun ActivityResultCaller.registerForEnableBluetoothResult(
    onBluetoothEnabled: BluetoothScope.(Boolean) -> Unit
): ActivityResultLauncher<Unit> {
    var enableBluetoothLauncher: ActivityResultLauncher<Unit>? = null
    enableBluetoothLauncher = registerForActivityResult(EnableBluetoothContract()) {
        onBluetoothEnabled(BluetoothScope { enableBluetoothLauncher?.launch() }, it)
    }
    return enableBluetoothLauncher
}

class EnableBluetoothContract : ActivityResultContract<Unit, Boolean>() {
    override fun createIntent(context: Context, input: Unit): Intent =
        Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)

    override fun parseResult(resultCode: Int, intent: Intent?): Boolean =
        resultCode == Activity.RESULT_OK

    override fun getSynchronousResult(context: Context, input: Unit): SynchronousResult<Boolean>? =
        if (isBluetoothEnabled) SynchronousResult(true) else null
}

fun interface BluetoothScope {
    fun enableBluetooth()
}