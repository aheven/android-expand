package heven.holt.expand.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import heven.holt.expand.architecture.title.HeaderStateView
import heven.holt.expand.demo.databinding.ViewCommonBinding

/**
 * <pre>
 *     author : HevenHolt
 *     e-mail : hyt0302@qq.com
 *     time   : 2023/08/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class NormalHeaderDelegate : HeaderStateView.ViewDelegate("NormalHeaderDelegate") {
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup): View {
        val binding = ViewCommonBinding.inflate(inflater)
        return binding.root
    }
}