package ir.amir.isnta.presenter.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.amir.isnta.data.dataSore.DataStore
import ir.amir.isnta.presenter.base.BaseViewModel
import ir.amir.isnta.presenter.base.UiState
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val dataStore: DataStore)
    : BaseViewModel<LoginState, LoginEvent>() {

    var firstTime = true

    override fun createInitialState() = LoginState.IDLE

    override fun handleEvents() {
        viewModelScope.launch {
            _event.consumeAsFlow().collect { event ->
                when (event) {
                    is LoginEvent.GetLocal -> {
                        val languageId = dataStore.getLocalApp()
                        _state.emit(LoginState.GetLocale(languageId))
                    }
                    is LoginEvent.ChangeLocale -> {
                        dataStore.setLocalApp(event.languageId)
                        _state.emit(LoginState.ChangeLocal)
                    }
                }
            }
        }
    }
}