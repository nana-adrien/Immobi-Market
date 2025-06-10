package empire.digiprem.core.utils

fun Any.getName(): String {
   println("this::class.toString()=${this::class.simpleName}")
    return this::class.toString().split(".").last().split(" ").first()
}