package ir.amir.isnta.presenter.main

import ir.amir.isnta.presenter.base.UiState

sealed class MainState : UiState {

    data class ChangeLocal(val languageId: String) : MainState()
    object SetContentView : MainState()
}

