package ir.amir.isnta.presenter.base


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BaseViewModel<US : UiState, UI : UiIntent> : ViewModel() {

    open val intent = Channel<UI>()
    open val state = MutableSharedFlow<US>()

    abstract fun handleIntent()
}