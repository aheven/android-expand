package heven.holt.expand.ktx

import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File

lateinit var fileProviderAuthority: String

inline val EXTERNAL_MEDIA_IMAGES_URI: Uri
    get() = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

inline val EXTERNAL_MEDIA_VIDEO_URI: Uri
    get() = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

fun File.toUri(authority: String = fileProviderAuthority): Uri =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        FileProvider.getUriForFile(application, authority, this)
    } else {
        Uri.fromFile(this)
    }