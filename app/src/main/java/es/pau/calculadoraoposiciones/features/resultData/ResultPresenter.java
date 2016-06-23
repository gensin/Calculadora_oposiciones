package es.pau.calculadoraoposiciones.features.resultData;

import org.webpartners.meigic.presenters.MeigicPresenter;

/**
 * Created by pau on 23/06/16.
 */
public class ResultPresenter extends MeigicPresenter<ResultView> {

    private int total, taken, studied;

    public ResultPresenter(ResultView view) {
        super(view);
    }

    @Override public void setup() {
        view.showLoading();
    }

    public void setVariables(int total, int taken, int studied) {
        this.total = total;
        this.taken = taken;
        this.studied = studied;
    }

    public void recalculate(String newStudied) {
        if(newStudied != null)
            this.studied = Integer.valueOf(newStudied);
    }

    public void calculate(String savedCalculation) {
        if (!savedCalculation.isEmpty()) {

        }
    }
}
