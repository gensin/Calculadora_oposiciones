package es.pau.calculadoraoposiciones.features.enterData;

import org.webpartners.meigic.views.MeigicActivity;

import es.pau.calculadoraoposiciones.R;

/**
 * Created by pau on 22/06/16.
 */
public class EnterData extends MeigicActivity<EnterDataPresenter, EnterDataView> implements EnterDataView {

    @Override protected EnterDataPresenter initPresenter() {
        return new EnterDataPresenter(this);
    }

    @Override protected void initView() {
        setContentView(R.layout.activity_data);
    }
}
