package es.pau.calculadoraoposiciones.features.enterData;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.webpartners.meigic.views.MeigicActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.pau.calculadoraoposiciones.R;

/**
 * Created by pau on 22/06/16.
 */
public class EnterData extends MeigicActivity<EnterDataPresenter, EnterDataView> implements EnterDataView {

    @BindView(R.id.total_topics) EditText totalTopics;
    @BindView(R.id.taken_topics) EditText takenTopics;
    @BindView(R.id.studied_topics) EditText studiedTopics;
    @BindView(R.id.calculate) Button calculate;

    @Override
    protected EnterDataPresenter initPresenter() {
        return new EnterDataPresenter(this);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_data);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.calculate) public void onCalculateButtonClick() {
    }
}
