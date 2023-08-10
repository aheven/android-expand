package heven.holt.expand.architecture.title

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import androidx.annotation.DrawableRes
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

enum class NavBtnType {
    ICON, TEXT, ICON_TEXT, NONE
}

class ToolbarConfig(
    var title: String? = null,
    var navBtnType: NavBtnType = NavBtnType.ICON,
    val extras: HashMap<String, Any?> = HashMap(),
) {
    @DrawableRes
    var navIcon: Int? = null
    var navText: String? = null
        private set
    var onNavClickListener = View.OnClickListener {
        var context: Context? = it.context
        while (context is ContextWrapper) {
            if (context is Activity) {
                context.finish()
                return@OnClickListener
            }
            context = context.baseContext
        }
    }
        private set

    @DrawableRes
    var rightIcon: Int? = null
        private set
    var rightText: String? = null
        private set
    var onRightClickListener: View.OnClickListener? = null
        private set

    fun navIcon(@DrawableRes icon: Int? = navIcon, listener: View.OnClickListener) {
        navIcon = icon
        onNavClickListener = listener
    }

    fun navText(text: String, listener: View.OnClickListener) {
        navText = text
        onNavClickListener = listener
    }

    fun rightIcon(@DrawableRes icon: Int, listener: View.OnClickListener) {
        rightIcon = icon
        onRightClickListener = listener
    }

    fun rightText(text: String, listener: View.OnClickListener) {
        rightText = text
        onRightClickListener = listener
    }
}

fun <T> toolbarExtras() = object : ReadWriteProperty<ToolbarConfig, T?> {
    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: ToolbarConfig, property: KProperty<*>): T? =
        thisRef.extras[property.name] as? T

    override fun setValue(thisRef: ToolbarConfig, property: KProperty<*>, value: T?) {
        thisRef.extras[property.name] = value
    }
}