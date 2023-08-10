package heven.holt.expand.architecture.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.dylanc.viewbinding.base.ActivityBinding
import com.dylanc.viewbinding.base.ActivityBindingDelegate
import heven.holt.expand.architecture.title.Decorative
import heven.holt.expand.architecture.title.HeaderState
import heven.holt.expand.architecture.title.HeaderStateDelegate

/**
 * <pre>
 *     author : HevenHolt
 *     e-mail : hyt0302@qq.com
 *     time   : 2023/08/01
 *     desc   : 结合 ViewBindingKtx 的数据绑定基类
 *     version: 1.0
 * </pre>
 */
abstract class BaseBindingActivity<VB : ViewBinding> : BaseActivity(),
    HeaderState by HeaderStateDelegate(), Decorative,
    ActivityBinding<VB> by ActivityBindingDelegate() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentViewWithBinding()
        binding.root.decorate(this)
    }
}