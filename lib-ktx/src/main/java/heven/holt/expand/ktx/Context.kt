package heven.holt.expand.ktx

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

fun Context.asActivity(): Activity? =
    this as? Activity ?: (this as? ContextWrapper)?.baseContext as? Activity