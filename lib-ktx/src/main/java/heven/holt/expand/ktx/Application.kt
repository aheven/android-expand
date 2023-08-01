package heven.holt.expand.ktx

import android.app.Activity
import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES

lateinit var application: Application
    internal set

inline val isAppDebug: Boolean get() = application.isAppDebug

inline val Application.isAppDebug: Boolean
    get() = applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0

inline val isAppDarkMode: Boolean
    get() = (application.resources.configuration.uiMode and UI_MODE_NIGHT_MASK) == UI_MODE_NIGHT_YES

interface OnAppStatusChangedListener {
    fun onForeground(activity: Activity)
    fun onBackground(activity: Activity)
}