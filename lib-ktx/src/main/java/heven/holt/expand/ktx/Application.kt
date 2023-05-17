package heven.holt.expand.ktx

import android.app.Activity
import android.app.Application

lateinit var application: Application
    internal set

interface OnAppStatusChangedListener {
    fun onForeground(activity: Activity)
    fun onBackground(activity: Activity)
}