package empire.digiprem

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling


@SpringBootApplication
@EnableScheduling
class ImmobiMarketWebAppApplication

fun main(args: Array<String>) {
    runApplication<ImmobiMarketWebAppApplication>(*args)
}