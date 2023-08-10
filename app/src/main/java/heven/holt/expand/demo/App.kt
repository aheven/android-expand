package heven.holt.expand.demo

import android.app.Application
import heven.holt.expand.architecture.title.HeaderStateView
import heven.holt.expand.delegate.ToolbarDelegate
import heven.holt.expand.logger.AndroidLogAdapter
import heven.holt.expand.logger.DiskLogAdapter
import heven.holt.expand.logger.Logger
import skin.support.SkinCompatManager
import skin.support.app.SkinAppCompatViewInflater

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(AndroidLogAdapter())
            Logger.addLogAdapter(DiskLogAdapter(this))
        }
        Logger.json(json = "{\"name\":\"HevenHolt\",\"age\":18}")

        SkinCompatManager.withoutActivity(this)
            .addInflater(SkinAppCompatViewInflater())
            .loadSkin()

        HeaderStateView.setViewDelegatePool {
            register(ToolbarDelegate())
        }
    }
}