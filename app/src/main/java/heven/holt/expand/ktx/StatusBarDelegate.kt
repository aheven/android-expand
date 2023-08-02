package heven.holt.expand.ktx

import android.content.res.Configuration
import heven.holt.expand.demo.StatusBarDelegate
import skin.support.SkinCompatManager

/**
 * <pre>
 *     author : HevenHolt
 *     e-mail : hyt0302@qq.com
 *     time   : 2023/08/01
 *     desc   : 换肤与状态栏操作设置
 *     version: 1.0
 * </pre>
 */
fun Configuration.onConfigurationChanged(){
    when (uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> {
            SkinCompatManager.getInstance()
                .loadSkin("night.skin", SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS)
        }

        Configuration.UI_MODE_NIGHT_NO -> {
            SkinCompatManager.getInstance().restoreDefaultTheme()
        }
    }
}

fun applyLightOrNightSkin(): StatusBarDelegate.StatusBarStyle {
    val isNight = SkinCompatManager.getInstance().curSkinName == "night.skin"
    return if (isNight) StatusBarDelegate.StatusBarStyle.NIGHT else StatusBarDelegate.StatusBarStyle.LIGHT
}