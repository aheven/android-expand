package heven.holt.expand.logger

/**
 * [LogAdapter]的Android终端日志输出实现
 * 将输出打印到带有漂亮边框的LogCat
 *
 * <pre>
 * ┌──────────────────────────
 * │ 堆栈历史
 * ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
 * │ 日志信息
 * └──────────────────────────
 * </pre>
 */
class AndroidLogAdapter : LogAdapter {

    private val formatStrategy: FormatStrategy

    constructor() {
        formatStrategy = PrettyFormatStrategy.newBuilder().build()
    }

    constructor(formatStrategy: FormatStrategy) {
        this.formatStrategy = Utils.checkNotNull(formatStrategy)
    }

    override fun isLoggable(priority: Int, tag: String?): Boolean {
        return true
    }

    override fun log(priority: Int, tag: String?, message: String) {
        formatStrategy.log(priority, tag, message)
    }
}