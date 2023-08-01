package heven.holt.expand.ktx

import android.view.View

inline fun View.doOnClick(crossinline block: (View) -> Unit) = setOnClickListener { block(this) }