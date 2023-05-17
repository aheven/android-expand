package heven.holt.expand.demo

import android.app.Application
import heven.holt.expand.logger.AndroidLogAdapter
import heven.holt.expand.logger.DiskLogAdapter
import heven.holt.expand.logger.Logger

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(AndroidLogAdapter())
            Logger.addLogAdapter(DiskLogAdapter(this))
        }
        Logger.json(json = "{\"name\":\"HevenHolt\",\"age\":18}")
    }
}