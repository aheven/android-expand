package heven.holt.expand.logger

/**
 * 确定日志的目标，例如磁盘、Logcat等
 *
 * @see LogcatLogStrategy
 * @see DiskLogStrategy
 */
interface LogStrategy {
    /**
     * 每次处理日志消息时，Logger都会调用它。
     * 整个管道中日志的最后目的地
     *
     * @param priority 日志级别，例如 DEBUG WARNING
     * @param tag      日志消息的给定标签
     * @param message  日志消息
     */
    fun log(priority: Int, tag: String?, message: String)
}