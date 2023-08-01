package heven.holt.expand.demo

import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.WindowCompat
import skin.support.SkinCompatManager
import kotlin.reflect.KProperty

/**
 * <pre>
 *     author : HevenHolt
 *     e-mail : hyt0302@qq.com
 *     time   : 2023/08/01
 *     desc   : 状态栏委托类
 *     version: 1.0
 * </pre>
 */
class StatusBarDelegate {
    private var style: StatusBarStyle = StatusBarStyle.LIGHT

    private var rootView: View? = null

    operator fun getValue(
        baseActivity: BaseActivity,
        property: KProperty<*>
    ): StatusBarStyle {
        return style
    }

    operator fun setValue(
        baseActivity: BaseActivity,
        property: KProperty<*>,
        statusBarStyle: StatusBarStyle
    ) {
        this.style = statusBarStyle
        baseActivity.run {
            rootView ?: findViewById<View?>(android.R.id.content)?.also { rootView = it } ?: return
            when (style) {
                StatusBarStyle.LIGHT -> {
                    window.statusBarColor =
                        ResourcesCompat.getColor(resources, R.color.window_background, theme)

                    WindowCompat.getInsetsController(
                        window,
                        rootView!!
                    ).isAppearanceLightStatusBars = true
                }

                StatusBarStyle.NIGHT -> {
                    window.statusBarColor =
                        ResourcesCompat.getColor(resources, R.color.window_background_night, theme)

                    WindowCompat.getInsetsController(
                        window,
                        rootView!!
                    ).isAppearanceLightStatusBars = false
                }
            }
        }

    }

    enum class StatusBarStyle {
        LIGHT, NIGHT
    }
}