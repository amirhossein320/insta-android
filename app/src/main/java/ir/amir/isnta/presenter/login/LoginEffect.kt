package ir.amir.isnta.presenter.login

import ir.amir.isnta.presenter.base.UiEffect

sealed class LoginEffect : UiEffect {

    object ChangeLocal : LoginEffect()
    object Signup : LoginEffect()
    object Login : LoginEffect()
    object ForgePassword : LoginEffect()
}
