package heven.holt.expand.widget

import android.content.Context
import android.util.AttributeSet
import com.dylanc.viewbinding.inflate
import heven.holt.expand.demo.databinding.ToolbarBinding
import skin.support.widget.SkinCompatRelativeLayout

/**
 * <pre>
 *     author : HevenHolt
 *     e-mail : hyt0302@qq.com
 *     time   : 2023/08/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    SkinCompatRelativeLayout(context, attrs, defStyleAttr) {
    private val binding = inflate<ToolbarBinding>()

    fun setTitle(title: String) {
        binding.title.text = title
    }
}