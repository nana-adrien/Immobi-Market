package empire.digiprem.core.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel


val commonModules = module {
  viewModel { ExempleViewModel() }
}
expect val initializeModules: Module

fun InitializeKoin(
    config: (KoinApplication.() -> Unit)? = null
) {
    startKoin {
        config?.invoke(this)
        modules(
            listOf(
                commonModules,
                initializeModules
            )
        )

    }


}