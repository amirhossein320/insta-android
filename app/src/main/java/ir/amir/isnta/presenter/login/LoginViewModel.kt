package ir.amir.isnta.presenter.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.amir.isnta.data.dataSore.DataStore
import ir.amir.isnta.data.repository.AuthenticationRepository
import ir.amir.isnta.data.repository.RepositoryResult
import ir.amir.isnta.data.service.auth.UserResponse
import ir.amir.isnta.presenter.base.BaseViewModel
import ir.amir.isnta.presenter.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthenticationRepository,
    private val dataStore: DataStore
) :
    BaseViewModel<LoginState, LoginEvent, LoginEffect>() {

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
                        _effect.send(LoginEffect.ChangeLocal)
                    }
                    is LoginEvent.ForgetPassword -> {
                        _effect.send(LoginEffect.ForgePassword)
                    }
                    is LoginEvent.Login -> login(event.username, event.password)
                }
            }
        }
    }


    private fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.login(username, password).collect {result->
                when(result){
                    is RepositoryResult.Loading -> _state.emit(LoginState.Login.Loading)
                    is RepositoryResult.Error -> _state.emit(LoginState.Login.Error(result.message))
                    is RepositoryResult.Data<*> -> _state.emit(LoginState.Login.Success)
                }
            }
        }
    }
}