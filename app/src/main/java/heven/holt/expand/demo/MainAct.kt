package heven.holt.expand.demo

import android.os.Bundle
import heven.holt.expand.architecture.channel.receiveTag
import heven.holt.expand.delegate.NormalHeaderDelegate
import heven.holt.expand.delegate.ScrollingDecorViewDelegate
import heven.holt.expand.delegate.ToolbarDelegate
import heven.holt.expand.demo.databinding.ActMainBinding
import heven.holt.expand.ktx.doOnClick
import heven.holt.expand.ktx.isActivityExistsInStack
import heven.holt.expand.logger.Logger
import skin.support.SkinCompatManager

class MainAct : BaseBindingActivity<ActMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDecorView(ScrollingDecorViewDelegate("111"))
//        setHeaders(ToolbarViewDelegate("首页"), NormalHeaderDelegate())

        receiveTag("customTag") {
            Logger.d("receiveTag: $it")
        }

//        binding.textView.doOnClick {
//            if (it.isSelected) {
//                statusBarStyle = StatusBarDelegate.StatusBarStyle.LIGHT
//                SkinCompatManager.getInstance().restoreDefaultTheme()
//            } else {
//                statusBarStyle = StatusBarDelegate.StatusBarStyle.NIGHT
//                SkinCompatManager.getInstance()
//                    .loadSkin("night.skin", SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS)
//            }
//            it.isSelected = !it.isSelected
////            startActivity<SecondAct>()
//        }

        Logger.d(isActivityExistsInStack<MainAct>())
        Logger.d(isActivityExistsInStack<SecondAct>())
    }
}