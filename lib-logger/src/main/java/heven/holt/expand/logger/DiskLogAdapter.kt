package heven.holt.expand.logger

class DiskLogAdapter : LogAdapter {
    private val formatStrategy: FormatStrategy

    constructor() {
        formatStrategy = CsvFormatStrategy.newBuilder().build()
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