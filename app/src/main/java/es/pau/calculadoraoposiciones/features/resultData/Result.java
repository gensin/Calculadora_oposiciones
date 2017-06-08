package es.pau.calculadoraoposiciones.features.resultData;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
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
        return new ResultPresenter(this,
                getIntent().getIntExtra(EnterData.TOTAL_SAVED,0),
                getIntent().getIntExtra(EnterData.TAKEN_SAVED,0),
                getIntent().getIntExtra(EnterData.STUDIED_SAVED,0));
    }

    @Override protected void initView() {
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        newTopics.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO)
                    recalculate.performClick();
                return true;
            }
        });
        newTopics.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override public void afterTextChanged(Editable s) {
                presenter.setButtonEnable(s.toString());
            }
        });

        SharedPreferences prefs = getSharedPreferences(EnterData.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        presenter.calculate(prefs.getString(EnterData.RESULT_SAVED,""));
    }

    @OnClick(R.id.recalculate) public void onRecalculateClick() {
        presenter.recalculate(newTopics.getText().toString());
    }

    @Override public void showLoading() {
        percentage.setVisibility(View.INVISIBLE);
        calculateProgress.setVisibility(View.VISIBLE);
    }

    @Override public void hideLoading() {
        percentage.setVisibility(View.VISIBLE);
        calculateProgress.setVisibility(View.INVISIBLE);
    }

    @Override public void showPercentage(String percentageText) {
        // TODO: 20/09/2016 Guardar porcentaje en shared preferences
        percentage.setText(percentageText);
    }

    @Override public void setButtonEnable(boolean isEnable) {
        recalculate.setEnabled(isEnable);
    }

    @Override public void changeButtonColor(boolean isEnable) {
        if (recalculate.isEnabled()) {
            recalculate.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        } else {
            recalculate.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryNotEnabled));
        }
    }
}
