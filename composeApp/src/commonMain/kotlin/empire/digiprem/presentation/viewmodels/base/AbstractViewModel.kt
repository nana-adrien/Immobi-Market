package empire.digiprem.presentation.viewmodels.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class AbstractViewModel<model, intent>(private val defaultState: model) : ViewModel() {

    protected val _mutableState = MutableStateFlow(defaultState)
    val state = _mutableState.asStateFlow()

    abstract fun onIntentHandler(intent: intent)
}