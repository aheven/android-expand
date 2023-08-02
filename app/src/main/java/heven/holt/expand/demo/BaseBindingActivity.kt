package heven.holt.expand.demo

import android.content.res.Configuration
import androidx.viewbinding.ViewBinding
import heven.holt.expand.architecture.base.BaseBindingActivity
import heven.holt.expand.ktx.applyLightOrNightSkin
import heven.holt.expand.ktx.onConfigurationChanged

open class BaseBindingActivity<VB : ViewBinding> : BaseBindingActivity<VB>() {

    protected var statusBarStyle: StatusBarDelegate.StatusBarStyle by StatusBarDelegate()

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        newConfig.onConfigurationChanged()
    }

    override fun applySkin() {
        statusBarStyle = applyLightOrNightSkin()
    }
}