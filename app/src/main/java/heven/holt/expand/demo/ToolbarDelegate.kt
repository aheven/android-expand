package heven.holt.expand.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dylanc.loadingstateview.BaseToolbarViewDelegate
import com.dylanc.loadingstateview.ToolbarConfig
import heven.holt.expand.widget.CustomToolbar

/**
 * <pre>
 *     author : HevenHolt
 *     e-mail : hyt0302@qq.com
 *     time   : 2023/08/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class ToolbarDelegate : BaseToolbarViewDelegate() {

    private lateinit var toolbar: CustomToolbar

    override fun onBindToolbar(config: ToolbarConfig) {
        config.title?.let { toolbar.setTitle(it) }
    }

    override fun onCreateToolbar(inflater: LayoutInflater, parent: ViewGroup): View {
        return CustomToolbar(parent.context).also { toolbar = it }
    }
}