package heven.holt.expand.ktx

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import java.util.LinkedList

internal val activityCache = LinkedList<Activity>()

fun startActivity(intent: Intent) = topActivity.startActivity(intent)

inline fun <reified T : Activity> startActivity(
    vararg pairs: Pair<String, Any?>,
    crossinline block: Intent.() -> Unit = {}
) =
    topActivity.startActivity<T>(pairs = pairs, block = block)

fun Activity.finishWithResult(vararg pair: Pair<String, *>) {
    setResult(Activity.RESULT_OK, Intent().putExtras(bundleOf(*pair)))
    finish()
}

val activityList: List<Activity> get() = activityCache.toList()

val topActivity: Activity get() = activityCache.last()

val topActivityOrNull: Activity? get() = activityCache.lastOrNull()

inline fun <reified T : Activity> isActivityExistsInStack(): Boolean =
    isActivityExistsInStack(T::class.java)

fun <T : Activity> isActivityExistsInStack(clazz: Class<T>): Boolean =
    activityCache.any { it.javaClass.name == clazz.name }

inline fun <reified T : Activity> finishActivity(): Boolean = finishActivity(T::class.java)

fun <T : Activity> finishActivity(clazz: Class<T>): Boolean =
    activityCache.removeAll {
        if (it.javaClass.name == clazz.name) it.finish()
        it.javaClass.name == clazz.name
    }

inline fun <reified T : Activity> finishToActivity(): Boolean =
    finishToActivity(T::class.java)

fun <T : Activity> finishToActivity(clazz: Class<T>): Boolean {
    for (i in activityCache.count() - 1 downTo 0) {
        if (clazz.name == activityCache[i].javaClass.name) {
            return true
        }
        activityCache.removeAt(i).finish()
    }
    return false
}

fun finishAllActivities(): Boolean =
    activityCache.removeAll {
        it.finish()
        true
    }

inline fun <reified T : Activity> finishAllActivitiesExcept(): Boolean =
    finishAllActivitiesExcept(T::class.java)

fun <T : Activity> finishAllActivitiesExcept(clazz: Class<T>): Boolean =
    activityCache.removeAll {
        if (it.javaClass.name != clazz.name) it.finish()
        it.javaClass.name != clazz.name
    }

fun finishAllActivitiesExceptNewest(): Boolean =
    finishAllActivitiesExcept(topActivity.javaClass)

fun ComponentActivity.pressBackTwiceToExitApp(
    delayMillis: Long = 2000,
    owner: LifecycleOwner = this,
    onFirstBackPressed: () -> Unit
) = onBackPressedDispatcher.addCallback(owner, object : OnBackPressedCallback(true) {
    private var lastBackTime: Long = 0

    override fun handleOnBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastBackTime > delayMillis) {
            onFirstBackPressed()
            lastBackTime = currentTime
        } else {
            finishAllActivities()
        }
    }
})