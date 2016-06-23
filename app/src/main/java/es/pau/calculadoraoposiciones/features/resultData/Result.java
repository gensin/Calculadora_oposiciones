package es.pau.calculadoraoposiciones.features.resultData;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.webpartners.meigic.views.MeigicActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.pau.calculadoraoposiciones.R;
import es.pau.calculadoraoposiciones.features.enterData.EnterData;

/**
 * Created by pau on 23/06/16.
 */
public class Result extends MeigicActivity<ResultPresenter, ResultView> implements ResultView {

    @BindView(R.id.percentage) TextView percentage;
    @BindView(R.id.calculate_progress) ProgressBar calculateProgress;
    @BindView(R.id.new_topics) EditText newTopics;
    @BindView(R.id.recalculate) Button recalculate;

    @Override protected ResultPresenter initPresenter() {
        return new ResultPresenter(this);
    }

    @Override protected void initView() {
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        presenter.setVariables(getIntent().getIntExtra(EnterData.TOTAL_SAVED,0),
                getIntent().getIntExtra(EnterData.TAKEN_SAVED,0),
                getIntent().getIntExtra(EnterData.STUDIED_SAVED,0));

        newTopics.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO)
                    recalculate.performClick();
                return true;
            }
        });

        SharedPreferences prefs = getSharedPreferences(EnterData.SHARED_PREFERENCES, Context.MODE_PRIVATE);

        presenter.calculate(prefs.getString(EnterData.RESULT_SAVED,""));

    }

    @OnClick(R.id.recalculate) public void onRecalculateClick() {
        presenter.recalculate(newTopics.getText().toString());
    }
}
