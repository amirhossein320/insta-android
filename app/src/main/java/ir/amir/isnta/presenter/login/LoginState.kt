package ir.amir.isnta.presenter.login

import ir.amir.isnta.presenter.base.UiState

sealed class LoginState : UiState {

    object IDLE : LoginState()
    data class GetLocale(val languageId: String) : LoginState()

    sealed class Login {
        object Loading : LoginState()
        object Success : LoginState()
        data class Error(val message: String) : LoginState()
    }
}
