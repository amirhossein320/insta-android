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
    BaseViewModel<MainState, MainIntent>() {


    override fun handleIntent() {
        viewModelScope.launch {
            intent.consumeAsFlow().collect { mainState ->
                when (mainState) {
                    is MainIntent.GetLocal -> {
                        val local = dataStore.getLocalApp()
                        state.emit(MainState.ChangeLocal("fa"))
                    }
                }
            }
        }
    }

}