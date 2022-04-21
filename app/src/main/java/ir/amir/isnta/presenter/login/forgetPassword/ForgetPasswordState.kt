package ir.amir.isnta.presenter.login.forgetPassword

import ir.amir.isnta.presenter.base.UiState

sealed class ForgetPasswordState : UiState {

    object IDLE : ForgetPasswordState()
    object ChangeLocal : ForgetPasswordState()
    object Signup : ForgetPasswordState()
    object ForgePassword : ForgetPasswordState()
    data class GetLocale(val languageId: String) : ForgetPasswordState()

    sealed class Login {
        object Loading : ForgetPasswordState()
        object Success : ForgetPasswordState()
        data class Error(val message: String) : ForgetPasswordState()
    }
}
