package empire.digiprem.app.config

expect object Log {
    fun d(tag: String?, msg: String)
    fun i(tag: String?, msg: String)
    fun w(tag: String?, msg: String)
    fun e(tag: String?, msg: String)
}