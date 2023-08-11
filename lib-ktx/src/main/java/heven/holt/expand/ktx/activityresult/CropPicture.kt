package heven.holt.expand.ktx.activityresult

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContract
import heven.holt.expand.ktx.contentResolver
import heven.holt.expand.ktx.grantReadUriPermission
import heven.holt.expand.ktx.insertMediaImage

fun ActivityResultCaller.registerForCropPictureResult(callback: ActivityResultCallback<Uri?>) =
    registerForActivityResult(CropPictureContract(), callback)

class CropPictureRequest constructor(
    val inputUri: Uri,
    val outputUri: Uri? = null,
    val extras: Bundle = Bundle()
)

open class CropPictureContract : ActivityResultContract<CropPictureRequest, Uri?>() {

    private var outputUri: Uri? = null
    override fun createIntent(context: Context, input: CropPictureRequest): Intent {
        outputUri = input.outputUri ?: contentResolver.insertMediaImage()
        return Intent("com.android.camera.action.CROP")
            .setDataAndType(input.inputUri, "image/*")
            .putExtra(MediaStore.EXTRA_OUTPUT, outputUri)
            .putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())
            .putExtra("return-data", false)
            .putExtras(input.extras)
            .grantReadUriPermission()
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? = outputUri
}