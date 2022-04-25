package ir.amir.isnta.presenter.login.forgetPassword

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.amir.isnta.data.dataSore.DataStore
import ir.amir.isnta.presenter.base.BaseViewModel
import ir.amir.isnta.presenter.login.LoginEffect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(private val dataStore: DataStore) :
    BaseViewModel<ForgetPasswordState, ForgetPasswordEvent,LoginEffect>() {

    var firstTime = true

    override fun createInitialState() = ForgetPasswordState.IDLE

    override fun handleEvents() {
        viewModelScope.launch {
            _event.consumeAsFlow().collect { event ->
                when (event) {
                    is ForgetPasswordEvent.GetLocal -> {
                        val languageId = dataStore.getLocalApp()
                        _state.emit(ForgetPasswordState.GetLocale(languageId))
                    }
                    is ForgetPasswordEvent.ChangeLocale -> {
                        dataStore.setLocalApp(event.languageId)
                        _state.emit(ForgetPasswordState.ChangeLocal)
                    }
                    is ForgetPasswordEvent.ForgetPassword -> {
                        _state.emit(ForgetPasswordState.ForgePassword)
                    }
                }
            }
        }
    }
}