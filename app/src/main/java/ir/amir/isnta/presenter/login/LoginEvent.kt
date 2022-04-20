package ir.amir.isnta.presenter.login

import ir.amir.isnta.presenter.base.UiEvent

sealed class LoginEvent : UiEvent {

    data class ChangeLocale(val languageId: String) : LoginEvent()
    data class Login(val username: String, val password: String) : LoginEvent()
    object GetLocal : LoginEvent()
    object Signup : LoginEvent()
    object ForgetPassword : LoginEvent()

}