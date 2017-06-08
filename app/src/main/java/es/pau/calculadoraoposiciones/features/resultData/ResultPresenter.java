package es.pau.calculadoraoposiciones.features.resultData;

import org.webpartners.meigic.presenters.MeigicPresenter;

import utils.CalculationUtils;

/**
 * Project: Calculadoradeprobabilidaddeoposiciones
 * Created on 23/06/16.
 */
public class ResultPresenter extends MeigicPresenter<ResultView> {

    private int total, taken, studied;

    public ResultPresenter(ResultView view) {
        super(view);
    }

    public ResultPresenter(ResultView view, int total, int taken, int studied) {
        super(view);
        this.total = total;
        this.taken = taken;
        this.studied = studied;
    }

    @Override public void setup() {
        view.showLoading();
    }

    public void recalculate(String newStudied) {
        if(newStudied != null) {
            this.studied = Integer.valueOf(newStudied);
        }
    }

    public void calculate(String savedCalculation) {
        if (!savedCalculation.isEmpty()) {
            view.showPercentage(savedCalculation);
            view.hideLoading();
        } else {
            view.showPercentage(CalculationUtils.probabilityPercentage(total, taken, studied));
            view.hideLoading();
        }
    }

    public void setButtonEnable(String newTopics) {
        boolean isEnable = false;
        if(newTopics != null && !newTopics.isEmpty()) {
            view.setButtonEnable(isEnable);
            view.changeButtonColor(isEnable);
        }
    }
}
