package heven.holt.expand.demo

import android.app.Application
import heven.holt.expand.logger.AndroidLogAdapter
import heven.holt.expand.logger.Logger

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Logger.addLogAdapter(AndroidLogAdapter())
        Logger.json(json = "{\"name\":\"HevenHolt\",\"age\":18}")
    }
}