package heven.holt.expand.logger

/**
 * 在给定的日志消息周围绘制边框以及其他信息，例如：
 * * 线程信息
 * * 方法堆栈跟踪
 *
 * <pre>
 * ┌──────────────────────────
 * │ 堆栈历史
 * ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
 * │ 线程信息
 * ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
 * │ 日志信息
 * └──────────────────────────
 * </pre>
 *
 *  * <h3>自定义</h3>
 * <pre>
 * FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
 * .showThreadInfo(false)  // (可选) 是否显示线程信息. 默认为 true
 * .methodCount(0)         // (可选) 显示多少方法行. 默认为 2
 * .methodOffset(7)        // (可选) 隐藏内部方法调用的偏移量. 默认为 5
 * .logStrategy(customLog) // (可选) 更改要打印的日志策略. 默认为 LogCat
 * .tag("My custom tag")   // (可选) 每个日志的全局标签. 默认为 PRETTY_LOGGER
 * .build();
`</pre> *
 */
class PrettyFormatStrategy private constructor(builder: Builder) : FormatStrategy {

    private val methodCount: Int
    private val methodOffset: Int
    private val showThreadInfo: Boolean
    private val logStrategy: LogStrategy
    private val tag: String?

    override fun log(priority: Int, onceOnlyTag: String?, message: String) {
        Utils.checkNotNull(message)
        val tag = formatTag(onceOnlyTag)
        logTopBorder(priority, tag)
        logHeaderContent(priority, tag, methodCount)

        //get bytes of message with system's default charset (which is UTF-8 for Android)
        val bytes = message.toByteArray()
        val length = bytes.size
        if (length <= CHUNK_SIZE) {
            if (methodCount > 0) {
                logDivider(priority, tag)
            }
            logContent(priority, tag, message)
            logBottomBorder(priority, tag)
            return
        }
        if (methodCount > 0) {
            logDivider(priority, tag)
        }
        var i = 0
        while (i < length) {
            val count = Math.min(length - i, CHUNK_SIZE)
            //create a new String with system's default charset (which is UTF-8 for Android)
            logContent(priority, tag, String(bytes, i, count))
            i += CHUNK_SIZE
        }
        logBottomBorder(priority, tag)
    }

    private fun logTopBorder(logType: Int, tag: String?) {
        logChunk(logType, tag, TOP_BORDER)
    }

    private fun logHeaderContent(logType: Int, tag: String?, methodCount: Int) {
        var methodCount = methodCount
        val trace = Thread.currentThread().stackTrace
        if (showThreadInfo) {
            logChunk(
                logType,
                tag,
                HORIZONTAL_LINE.toString() + " Thread: " + Thread.currentThread().name
            )
            logDivider(logType, tag)
        }
        var level = ""
        val stackOffset = getStackOffset(trace) + methodOffset

        //corresponding method count with the current stack may exceeds the stack trace. Trims the count
        if (methodCount + stackOffset > trace.size) {
            methodCount = trace.size - stackOffset - 1
        }
        for (i in methodCount downTo 1) {
            val stackIndex = i + stackOffset
            if (stackIndex >= trace.size) {
                continue
            }
            val builder = StringBuilder()
            builder.append(HORIZONTAL_LINE)
                .append(' ')
                .append(level)
                .append(getSimpleClassName(trace[stackIndex].className))
                .append(".")
                .append(trace[stackIndex].methodName)
                .append(" ")
                .append(" (")
                .append(trace[stackIndex].fileName)
                .append(":")
                .append(trace[stackIndex].lineNumber)
                .append(")")
            level += "   "
            logChunk(logType, tag, builder.toString())
        }
    }

    private fun logBottomBorder(logType: Int, tag: String?) {
        logChunk(logType, tag, BOTTOM_BORDER)
    }

    private fun logDivider(logType: Int, tag: String?) {
        logChunk(logType, tag, MIDDLE_BORDER)
    }

    private fun getSimpleClassName(name: String): String {
        Utils.checkNotNull(name)
        val lastIndex = name.lastIndexOf(".")
        return name.substring(lastIndex + 1)
    }

    private fun logContent(logType: Int, tag: String?, chunk: String) {
        Utils.checkNotNull(chunk)
        val lines = chunk.split(System.getProperty("line.separator")).toTypedArray()
        for (line in lines) {
            logChunk(logType, tag, HORIZONTAL_LINE.toString() + " " + line)
        }
    }

    private fun logChunk(priority: Int, tag: String?, chunk: String) {
        Utils.checkNotNull(chunk)
        logStrategy.log(priority, tag, chunk)
    }

    private fun getStackOffset(trace: Array<StackTraceElement>): Int {
        Utils.checkNotNull(trace)
        var i = MIN_STACK_OFFSET
        while (i < trace.size) {
            val e = trace[i]
            val name = e.className
            if (name != LoggerPrinter::class.java.name && name != Logger::class.java.name) {
                return --i
            }
            i++
        }
        return -1
    }


    private fun formatTag(tag: String?): String? {
        return if (!tag.isNullOrEmpty() && this.tag != tag)
            this.tag + "-" + tag
        else
            this.tag
    }

    class Builder {
        var methodCount = 2
        var methodOffset = 0
        var showThreadInfo = true
        var logStrategy: LogStrategy? = null
        var tag: String? = "PRETTY_LOGGER"

        fun methodCount(`val`: Int): Builder {
            methodCount = `val`
            return this
        }

        fun methodOffset(`val`: Int): Builder {
            methodOffset = `val`
            return this
        }

        fun showThreadInfo(`val`: Boolean): Builder {
            showThreadInfo = `val`
            return this
        }

        fun logStrategy(`val`: LogStrategy?): Builder {
            logStrategy = `val`
            return this
        }

        fun tag(tag: String?): Builder {
            this.tag = tag
            return this
        }

        fun build(): PrettyFormatStrategy {
            if (logStrategy == null) {
                logStrategy = LogcatLogStrategy()
            }
            return PrettyFormatStrategy(this)
        }
    }

    companion object {
        /**
         * Android 日志条目最大限制为~4076字节。
         * 所以4000字节用作块大小，因为默认字符集为UTF-8
         */
        private const val CHUNK_SIZE = 4000

        /**
         * 最少堆栈跟踪索引，在两次本机调用后从此类开始。
         */
        private const val MIN_STACK_OFFSET = 5

        /**
         * Drawing toolbox
         */
        private const val TOP_LEFT_CORNER = '┌'
        private const val BOTTOM_LEFT_CORNER = '└'
        private const val MIDDLE_CORNER = '├'
        private const val HORIZONTAL_LINE = '│'
        private const val DOUBLE_DIVIDER =
            "────────────────────────────────────────────────────────"
        private const val SINGLE_DIVIDER =
            "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄"
        private const val TOP_BORDER = TOP_LEFT_CORNER.toString() + DOUBLE_DIVIDER + DOUBLE_DIVIDER
        private const val BOTTOM_BORDER =
            BOTTOM_LEFT_CORNER.toString() + DOUBLE_DIVIDER + DOUBLE_DIVIDER
        private const val MIDDLE_BORDER = MIDDLE_CORNER.toString() + SINGLE_DIVIDER + SINGLE_DIVIDER
        fun newBuilder(): Builder {
            return Builder()
        }
    }

    init {
        Utils.checkNotNull(builder)
        methodCount = builder.methodCount
        methodOffset = builder.methodOffset
        showThreadInfo = builder.showThreadInfo
        logStrategy = builder.logStrategy!!
        tag = builder.tag
    }
}