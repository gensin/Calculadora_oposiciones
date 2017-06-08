package es.pau.calculadoraoposiciones.features.enterData;

import org.webpartners.meigic.views.MeigicView;

/**
 * Created by pau on 22/06/16.
 */
public interface EnterDataView extends MeigicView {

    void changeTotalTopics(String total);

    void changeTakenTopics(String taken);

    void changeStudiedTopics(String studied);

    void setButtonEnable();

    void changeButtonColor();

    void showResults();
}
