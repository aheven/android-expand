package heven.holt.expand.logger

import android.util.Log

/**
 * [LogStrategy]的LogCat实现
 * 这只是使用标准[Log]类将所有日志打印到Logcat。
 */
@Suppress("NAME_SHADOWING")
class LogcatLogStrategy : LogStrategy {
    override fun log(priority: Int, tag: String?, message: String) {
        var tag = tag
        Utils.checkNotNull(message)
        if (tag == null) {
            tag = DEFAULT_TAG
        }
        Log.println(priority, tag, message)
    }

    companion object {
        const val DEFAULT_TAG = "NO_TAG"
    }
}