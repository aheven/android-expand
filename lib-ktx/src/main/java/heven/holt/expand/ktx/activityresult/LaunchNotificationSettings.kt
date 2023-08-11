package heven.holt.expand.ktx.activityresult

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContract

fun ActivityResultCaller.registerForLaunchNotificationSettingsResult(callback: ActivityResultCallback<Unit>) =
    registerForActivityResult(LaunchNotificationSettingsContract(), callback)

class LaunchNotificationSettingsContract : ActivityResultContract<Unit, Unit>() {
    override fun createIntent(context: Context, input: Unit): Intent =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                .putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
        } else {
            Intent("android.settings.APP_NOTIFICATION_SETTINGS")
                .putExtra("app_package", context.packageName)
                .putExtra("app_uid", context.applicationInfo.uid)
        }

    override fun parseResult(resultCode: Int, intent: Intent?) = Unit
}