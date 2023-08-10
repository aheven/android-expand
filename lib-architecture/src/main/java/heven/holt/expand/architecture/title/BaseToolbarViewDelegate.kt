package heven.holt.expand.architecture.title

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseToolbarViewDelegate : HeaderStateView.ViewDelegate(ViewType.TITLE) {
    internal lateinit var config: ToolbarConfig

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup) =
        onCreateToolbar(inflater, parent).apply { onBindToolbar(config) }

    abstract fun onCreateToolbar(inflater: LayoutInflater, parent: ViewGroup): View

    abstract fun onBindToolbar(config: ToolbarConfig)
}