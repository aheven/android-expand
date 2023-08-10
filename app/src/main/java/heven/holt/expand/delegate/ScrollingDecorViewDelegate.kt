package heven.holt.expand.delegate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import heven.holt.expand.architecture.title.HeaderStateView
import heven.holt.expand.demo.databinding.LayoutScrollingToolbarBinding

/**
 * <pre>
 *     author : HevenHolt
 *     e-mail : hyt0302@qq.com
 *     time   : 2023/08/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class ScrollingDecorViewDelegate(
    private val title: String
) : HeaderStateView.DecorViewDelegate() {

    private lateinit var binding: LayoutScrollingToolbarBinding

    override fun onCreateDecorView(context: Context, inflater: LayoutInflater): View {
        binding = LayoutScrollingToolbarBinding.inflate(inflater)
        binding.toolbar.setTitle(title)
        return binding.root
    }

    override fun getContentParent(decorView: View): ViewGroup {
        return binding.contentParent
    }
}