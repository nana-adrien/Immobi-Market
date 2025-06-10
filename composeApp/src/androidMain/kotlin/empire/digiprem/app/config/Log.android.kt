package empire.digiprem.app.config

import android.util.Log

actual object Log {
    actual fun d(tag: String?, msg: String) {
        Log.d(tag,msg)
    }
    actual fun i(tag: String?, msg: String) {
        Log.i(tag,msg)
    }
    actual fun w(tag: String?, msg: String) {
        Log.w(tag,msg)
    }
    actual fun e(tag: String?, msg: String) {
        Log.e(tag,msg)
    }
}