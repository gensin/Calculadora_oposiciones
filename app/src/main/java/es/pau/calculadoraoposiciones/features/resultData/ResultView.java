package es.pau.calculadoraoposiciones.features.resultData;

import org.webpartners.meigic.views.MeigicView;

/**
 * Created by pau on 23/06/16.
 */
public interface ResultView extends MeigicView {

    void showLoading();

    void hideLoading();

    void showPercentage(String percentage);

    void setButtonEnable(boolean isEnable);

    void changeButtonColor(boolean isEnable);
}
