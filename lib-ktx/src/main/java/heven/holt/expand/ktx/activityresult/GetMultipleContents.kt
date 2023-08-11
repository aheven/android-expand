package heven.holt.expand.ktx.activityresult

import android.net.Uri
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts

fun ActivityResultCaller.registerForGetMultipleContentsResult(callback: ActivityResultCallback<List<Uri>>) =
    MediaUriResultLauncher(registerForActivityResult(ActivityResultContracts.GetMultipleContents(), callback))