package heven.holt.expand.ktx.activityresult

import android.net.Uri
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts

fun ActivityResultCaller.registerForOpenDocumentTreeResult(callback: ActivityResultCallback<Uri?>) =
    registerForActivityResult(ActivityResultContracts.OpenDocumentTree(), callback)