package ir.amir.isnta.data.dataSore

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import ir.amir.isnta.util.SETTING_LOCAL_NAME
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


val Context.dataStore by preferencesDataStore("settings")

class DataStore(context: Context) {

    private val dataStore = context.dataStore

    suspend fun getLocalApp(): String = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey(SETTING_LOCAL_NAME)]
    }.first() ?: "en"

    suspend fun setLocalApp(languageCode:String){
        dataStore.edit {settings->
            settings[stringPreferencesKey(SETTING_LOCAL_NAME)] = languageCode
        }
    }
}