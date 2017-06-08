package es.pau.calculadoraoposiciones;

import android.app.Application;

/**
 * Project: Calculadoradeprobabilidaddeoposiciones
 * Created on 09/06/2017.
 */

public class CalculadoraApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Preferences.register(this);
    }
}
