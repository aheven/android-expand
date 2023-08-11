package heven.holt.expand.ktx

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf

inline fun <reified T> Context.intentOf(vararg pairs: Pair<String, *>): Intent =
    intentOf<T>(bundleOf(*pairs))

inline fun <reified T> Context.intentOf(bundle: Bundle): Intent =
    Intent(this, T::class.java).putExtras(bundle)

inline fun <reified T : Activity> Context.startActivity(
    vararg pairs: Pair<String, Any?>,
    crossinline block: Intent.() -> Unit = {}
) =
    startActivity(intentOf<T>(*pairs).apply(block))

fun Context.isPermissionGranted(permission: String): Boolean =
    ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Context.arePermissionsGranted(vararg permissions: String): Boolean =
    permissions.all { isPermissionGranted(it) }

fun Context.asActivity(): Activity? =
    this as? Activity ?: (this as? ContextWrapper)?.baseContext as? Activity