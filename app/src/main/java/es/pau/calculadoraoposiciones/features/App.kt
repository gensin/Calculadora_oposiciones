package es.pau.calculadoraoposiciones.features

import android.app.Application
import es.pau.calculadoraoposiciones.utils.Preferences

/**
 * Created on 13/06/18.
 */
val prefs: Preferences by lazy {
    App.prefs!!
}

class App : Application() {
    companion object {
        var prefs: Preferences? = null
    }

    override fun onCreate() {
        prefs = Preferences(applicationContext)
        super.onCreate()
    }
}