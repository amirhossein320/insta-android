package ir.amir.isnta.presenter.main

import ir.amir.isnta.presenter.base.UiState

sealed class MainState : UiState {

    object IDLE : MainState()
    object SetContentView : MainState()
    data class ChangeLocal(val languageId: String) : MainState()
}

