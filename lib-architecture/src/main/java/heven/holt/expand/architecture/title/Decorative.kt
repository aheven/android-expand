package heven.holt.expand.architecture.title

import android.view.View

interface Decorative {
    val isDecorated: Boolean get() = true
    val contentView: View? get() = null
}