package es.pau.calculadoraoposiciones;

import android.os.AsyncTask;

import static utils.CalculationUtilsKt.probabilityPercentage;

/**
 * Created by Pau on 29/04/2016.
 */
public class CalculatePercentageAsyncTask extends AsyncTask<Integer, Void, String> {

    @Override
    protected String doInBackground(Integer... userInfo) {
        return probabilityPercentage(userInfo[0],userInfo[1],userInfo[2]);
    }

}