package heven.holt.expand.ktx

import android.content.ContentResolver
import android.net.Uri
import androidx.core.content.contentValuesOf

inline val contentResolver: ContentResolver get() = application.contentResolver

fun ContentResolver.insert(uri: Uri, vararg pairs: Pair<String, Any?>): Uri? =
    contentResolver.insert(uri, contentValuesOf(*pairs))
fun ContentResolver.insertMediaImage(vararg pairs: Pair<String, Any?>): Uri? =
    contentResolver.insert(EXTERNAL_MEDIA_IMAGES_URI, *pairs)