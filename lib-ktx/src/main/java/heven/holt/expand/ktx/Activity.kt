package heven.holt.expand.ktx

import android.app.Activity
import java.util.LinkedList

internal val activityCache = LinkedList<Activity>()

inline fun <reified T : Activity> finishActivity(): Boolean = finishActivity(T::class.java)

fun <T : Activity> finishActivity(clazz: Class<T>): Boolean =
    activityCache.removeAll {
        if (it.javaClass.name == clazz.name) it.finish()
        it.javaClass.name == clazz.name
    }