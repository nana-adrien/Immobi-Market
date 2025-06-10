package empire.digiprem.app.config

actual object Log {
    actual fun d(tag: String?, msg: String) {
        println("DEBUG->$tag:$msg")
    }

    actual fun i(tag: String?, msg: String) {
        println("INFO->$tag:$msg")
    }

    actual fun w(tag: String?, msg: String) {
        println("WARNING->$tag:$msg")
    }

    actual fun e(tag: String?, msg: String) {
        println("ERROR->$tag:$msg")
    }
}