package ir.amir.isnta.presenter.main

import ir.amir.isnta.presenter.base.UiIntent

sealed class MainIntent : UiIntent {

    object GetLocal : MainIntent()
    object SetContentView : MainIntent()
}
