package ir.amir.isnta.presenter.main

import ir.amir.isnta.presenter.base.UiEvent

sealed class MainEvent : UiEvent {

    object GetLocal : MainEvent()
}
