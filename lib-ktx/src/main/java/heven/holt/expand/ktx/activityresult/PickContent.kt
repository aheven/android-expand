package heven.holt.expand.ktx.activityresult

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContract

fun ActivityResultCaller.registerForPickContentResult(callback: ActivityResultCallback<Uri?>) =
    MediaUriResultLauncher(registerForActivityResult(PickContentContract(), callback))

class PickContentContract : ActivityResultContract<String, Uri?>() {
    override fun createIntent(context: Context, input: String): Intent =
        Intent(Intent.ACTION_PICK).setType(input)
    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        return if (intent == null || resultCode != Activity.RESULT_OK) null else intent.data!!
    }
}