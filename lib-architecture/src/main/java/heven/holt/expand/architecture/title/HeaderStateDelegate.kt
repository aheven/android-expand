package heven.holt.expand.architecture.title

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

class HeaderStateDelegate : HeaderState {
    private var headerStateView: HeaderStateView? = null

    override fun Activity.decorateContentView(decorative: Decorative) {
        findViewById<ViewGroup>(android.R.id.content).getChildAt(0).decorate(decorative)
    }

    override fun View.decorate(decorative: Decorative): View =
        when {
            !decorative.isDecorated -> this
            decorative.contentView == null ->
                HeaderStateView(this).also { headerStateView = it }.decorView

            else -> {
                headerStateView = HeaderStateView(decorative.contentView!!)
                this
            }
        }

    override fun registerView(vararg viewDelegates: HeaderStateView.ViewDelegate) {
        headerStateView?.register(*viewDelegates)
    }

    override fun Activity.setToolbar(
        @StringRes titleId: Int,
        navBtnType: NavBtnType,
        block: (ToolbarConfig.() -> Unit)?
    ) {
        setToolbar(getString(titleId), navBtnType, block)
    }

    override fun Activity.setToolbar(
        title: String?,
        navBtnType: NavBtnType,
        block: (ToolbarConfig.() -> Unit)?
    ) {
        headerStateView?.setHeaders(ToolbarViewDelegate(title, navBtnType, block))
    }

    override fun Fragment.setToolbar(
        @StringRes titleId: Int,
        navBtnType: NavBtnType,
        block: (ToolbarConfig.() -> Unit)?
    ) {
        setToolbar(getString(titleId), navBtnType, block)
    }

    override fun Fragment.setToolbar(
        title: String?,
        navBtnType: NavBtnType,
        block: (ToolbarConfig.() -> Unit)?
    ) {
        headerStateView?.addChildHeaders(ToolbarViewDelegate(title, navBtnType, block))
    }

    override fun Activity.setHeaders(vararg delegates: HeaderStateView.ViewDelegate) {
        headerStateView?.setHeaders(*delegates)
    }

    override fun Fragment.setHeaders(vararg delegates: HeaderStateView.ViewDelegate) {
        headerStateView?.addChildHeaders(*delegates)
    }

    override fun Activity.setDecorView(delegate: HeaderStateView.DecorViewDelegate) {
        headerStateView?.setDecorView(delegate)
    }

    override fun Fragment.setDecorView(delegate: HeaderStateView.DecorViewDelegate) {
        headerStateView?.addChildDecorView(delegate)
    }

    override fun updateToolbar(block: ToolbarConfig.() -> Unit) {
        updateView<BaseToolbarViewDelegate>(ViewType.TITLE) { onBindToolbar(config.apply(block)) }
    }

    override fun <T : HeaderStateView.ViewDelegate> updateView(
        viewType: Any,
        block: T.() -> Unit
    ) {
        headerStateView?.updateViewDelegate(viewType, block)
    }

    override fun ToolbarViewDelegate(
        title: String?,
        navBtnType: NavBtnType,
        block: (ToolbarConfig.() -> Unit)?
    ) =
        requireNotNull(headerStateView?.getViewDelegate<BaseToolbarViewDelegate>(ViewType.TITLE)) {
            "ToolbarViewDelegate must be registered before."
        }.apply { config = ToolbarConfig(title, navBtnType).apply { block?.invoke(this) } }
}