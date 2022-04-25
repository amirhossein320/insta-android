package ir.amir.isnta.presenter.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.amir.isnta.data.dataSore.DataStore
import ir.amir.isnta.presenter.base.BaseViewModel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataStore: DataStore) :
    BaseViewModel<MainState, MainEvent,MainEffect>() {

    override fun createInitialState(): MainState  = MainState.IDLE

    override fun handleEvents() {
        viewModelScope.launch {
            _event.consumeAsFlow().collect { mainEvent ->
                when (mainEvent) {
                    is MainEvent.GetLocal -> {
                        val local = dataStore.getLocalApp()
                        _state.emit(MainState.ChangeLocal(local))
                        _state.emit(MainState.SetContentView)
                    }
                }
            }
        }
    }

   suspend fun getLocal() = dataStore.getLocalApp()
}