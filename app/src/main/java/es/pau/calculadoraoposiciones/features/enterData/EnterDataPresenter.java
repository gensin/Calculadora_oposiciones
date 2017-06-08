package es.pau.calculadoraoposiciones.features.enterData;

import android.content.SharedPreferences;
import android.text.Editable;

import org.webpartners.meigic.presenters.MeigicPresenter;

import es.pau.calculadoraoposiciones.Preferences;

/**
 * Project: Calculadoradeprobabilidaddeoposiciones
 * Created on 22/06/16.
 */
public class EnterDataPresenter extends MeigicPresenter<EnterDataView> {

    private static String DEFAULT_INPUT = "0";

    public EnterDataPresenter(EnterDataView view) {
        super(view);
    }

    @Override public void setup() {

        if(Preferences.hasPreference(Preferences.TOTAL_SAVED)){
            view.changeTotalTopics(Integer.toString(Preferences.loadInt(Preferences.TOTAL_SAVED)));
        }

        if(Preferences.hasPreference(Preferences.TAKEN_SAVED)){
            view.changeTakenTopics(Integer.toString(Preferences.loadInt(Preferences.TAKEN_SAVED)));
        }

        if(Preferences.hasPreference(Preferences.STUDIED_SAVED)){
            view.changeStudiedTopics(Integer.toString(Preferences.loadInt(Preferences.STUDIED_SAVED)));
        }

        view.setButtonEnable();
        view.changeButtonColor();
    }

    public void saveUserInput(Editable totalInput, Editable takenInput, Editable studiedInput) {
        String totalString;
        String takenString;
        String studiedString;
        if (totalInput == null) {
            totalString = DEFAULT_INPUT;
        } else {
            totalString = totalInput.toString();
        }
        if (takenInput == null) {
            takenString = DEFAULT_INPUT;
        }else {
           takenString = takenInput.toString();
        }
        if (studiedInput == null) {
            studiedString = DEFAULT_INPUT;
        } else {
            studiedString = studiedInput.toString();
        }

        this.saveUserInputString(totalString, takenString, studiedString);
        view.showResults();
    }

    private void saveUserInputString(String totalString, String takenString, String studiedString) {
        Preferences.saveInt(Preferences.TOTAL_SAVED, Integer.parseInt(totalString));
        Preferences.saveInt(Preferences.TAKEN_SAVED, Integer.parseInt(takenString));
        Preferences.saveInt(Preferences.STUDIED_SAVED, Integer.parseInt(studiedString));
    }

    public void refreshButton() {
        view.setButtonEnable();
        view.changeButtonColor();
    }
}
