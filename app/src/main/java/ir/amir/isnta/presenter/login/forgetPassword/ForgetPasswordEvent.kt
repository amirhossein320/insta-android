package ir.amir.isnta.presenter.login.forgetPassword

import ir.amir.isnta.presenter.base.UiEvent

sealed class ForgetPasswordEvent : UiEvent {

    data class ChangeLocale(val languageId: String) : ForgetPasswordEvent()
    data class Login(val username: String, val password: String) : ForgetPasswordEvent()
    object GetLocal : ForgetPasswordEvent()
    object Signup : ForgetPasswordEvent()
    object ForgetPassword : ForgetPasswordEvent()

}