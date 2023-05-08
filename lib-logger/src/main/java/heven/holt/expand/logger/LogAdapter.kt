package heven.holt.expand.logger

/**
 * 提供一个通用接口来发出日志。这是Logger的必须合约
 * @see AndroidLogAdapter
 * @see DiskLogAdapter
 */
interface LogAdapter {
    /**
     * 用于判断日志是否打印出来。
     *
     * @param priority 日志级别，例如 DEBUG WARNING
     * @param tag      日志消息的给定标签
     *
     * @return true 打印日志，false 不打印日志
     */
    fun isLoggable(priority: Int, tag: String?): Boolean

    /**
     * 每个日志都会使用这个管道
     *
     * @param priority 日志级别，例如 DEBUG WARNING
     * @param tag      日志消息的给定标签
     * @param message  日志消息
     */
    fun log(priority: Int, tag: String?, message: String)
}