package heven.holt.expand.logger

/**
 * 用于确定应如何打印或保存消息
 *
 * @see PrettyFormatStrategy
 * @see CsvFormatStrategy
 */
interface FormatStrategy {
    fun log(priority: Int, tag: String?, message: String)
}