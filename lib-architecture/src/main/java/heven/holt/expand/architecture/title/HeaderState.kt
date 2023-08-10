package heven.holt.expand.architecture.title

import android.app.Activity
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

interface HeaderState : Decorative {

    fun Activity.decorateContentView(decorative: Decorative)

    fun View.decorate(decorative: Decorative): View

    fun registerView(vararg viewDelegates: HeaderStateView.ViewDelegate)

    fun Activity.setToolbar(
        @StringRes titleId: Int,
        navBtnType: NavBtnType = NavBtnType.ICON,
        block: (ToolbarConfig.() -> Unit)? = null
    )

    fun Activity.setToolbar(
        title: String? = null,
        navBtnType: NavBtnType = NavBtnType.ICON,
        block: (ToolbarConfig.() -> Unit)? = null
    )

    fun Fragment.setToolbar(
        @StringRes titleId: Int,
        navBtnType: NavBtnType = NavBtnType.ICON,
        block: (ToolbarConfig.() -> Unit)? = null
    )

    fun Fragment.setToolbar(
        title: String? = null,
        navBtnType: NavBtnType = NavBtnType.ICON,
        block: (ToolbarConfig.() -> Unit)? = null
    )

    fun Activity.setHeaders(vararg delegates: HeaderStateView.ViewDelegate)

    fun Fragment.setHeaders(vararg delegates: HeaderStateView.ViewDelegate)

    fun Activity.setDecorView(delegate: HeaderStateView.DecorViewDelegate)

    fun Fragment.setDecorView(delegate: HeaderStateView.DecorViewDelegate)

    fun updateToolbar(block: ToolbarConfig.() -> Unit)

    fun <T : HeaderStateView.ViewDelegate> updateView(viewType: Any, block: T.() -> Unit)

    @Suppress("FunctionName")
    fun ToolbarViewDelegate(
        title: String? = null,
        navBtnType: NavBtnType = NavBtnType.ICON,
        block: (ToolbarConfig.() -> Unit)? = null
    ): BaseToolbarViewDelegate
}