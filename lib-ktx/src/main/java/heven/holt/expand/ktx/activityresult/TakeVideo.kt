package heven.holt.expand.ktx.activityresult

import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts
import heven.holt.expand.ktx.EXTERNAL_MEDIA_VIDEO_URI

fun ActivityResultCaller.registerForTakeVideoResult(callback: ActivityResultCallback<Boolean>) =
    SaveToUriLauncher(registerForActivityResult(ActivityResultContracts.CaptureVideo(), callback), EXTERNAL_MEDIA_VIDEO_URI, "mp4")