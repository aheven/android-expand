package heven.holt.expand.demo

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.SkinAppCompatDelegateImpl
import skin.support.SkinCompatManager
import skin.support.widget.SkinCompatSupportable

open class BaseActivity : AppCompatActivity(), SkinCompatSupportable {
    protected var statusBarStyle: StatusBarDelegate.StatusBarStyle by StatusBarDelegate()

    override fun getDelegate(): AppCompatDelegate {
        return SkinAppCompatDelegateImpl.get(this, this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when (newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                SkinCompatManager.getInstance()
                    .loadSkin("night.skin", SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS)
            }

            Configuration.UI_MODE_NIGHT_NO -> {
                SkinCompatManager.getInstance().restoreDefaultTheme()
            }
        }
    }

    override fun applySkin() {
        val isNight = SkinCompatManager.getInstance().curSkinName == "night.skin"
        statusBarStyle =
            if (isNight) StatusBarDelegate.StatusBarStyle.NIGHT else StatusBarDelegate.StatusBarStyle.LIGHT
    }
}