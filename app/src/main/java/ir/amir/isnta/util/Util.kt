package ir.amir.isnta.util

import android.content.Context
import android.os.Build
import java.util.*


fun Context.setLocalApp(languageCode: String) {
    val local = Locale(languageCode)
    Locale.setDefault(local)
    val config = resources.configuration
    config.setLocale(local)
    config.setLayoutDirection(local)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        createConfigurationContext(config)

    resources.updateConfiguration(config, resources.displayMetrics)
}