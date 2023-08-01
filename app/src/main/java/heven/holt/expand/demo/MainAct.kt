package heven.holt.expand.demo

import android.os.Bundle
import android.widget.TextView
import heven.holt.expand.ktx.asActivity
import heven.holt.expand.ktx.doOnClick
import heven.holt.expand.ktx.isActivityExistsInStack
import heven.holt.expand.ktx.startActivity
import heven.holt.expand.logger.Logger
import skin.support.SkinCompatManager

class MainAct : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

        val textView = findViewById<TextView>(R.id.text_view)
        Logger.d(textView.context.asActivity())
        textView.doOnClick {
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