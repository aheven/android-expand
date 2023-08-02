package heven.holt.expand.demo

import android.os.Bundle
import heven.holt.expand.demo.databinding.ActMainBinding
import heven.holt.expand.ktx.doOnClick
import heven.holt.expand.ktx.isActivityExistsInStack
import heven.holt.expand.logger.Logger
import skin.support.SkinCompatManager

class MainAct : BaseBindingActivity<ActMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setToolbar("主页")

        binding.textView.doOnClick {
            if (it.isSelected) {
                statusBarStyle = StatusBarDelegate.StatusBarStyle.LIGHT
                SkinCompatManager.getInstance().restoreDefaultTheme()
            } else {
                statusBarStyle = StatusBarDelegate.StatusBarStyle.NIGHT
                SkinCompatManager.getInstance()
                    .loadSkin("night.skin", SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS)
            }
            it.isSelected = !it.isSelected
//            startActivity<SecondAct>()
        }

        Logger.d(isActivityExistsInStack<MainAct>())
        Logger.d(isActivityExistsInStack<SecondAct>())
    }
}