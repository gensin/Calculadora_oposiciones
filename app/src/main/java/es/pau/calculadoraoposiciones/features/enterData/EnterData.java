package es.pau.calculadoraoposiciones.features.enterData;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.webpartners.meigic.views.MeigicActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.pau.calculadoraoposiciones.R;
import es.pau.calculadoraoposiciones.features.resultData.ResultActivity;

/**
 * Project: Calculadoradeprobabilidaddeoposiciones
 * Created on 22/06/16.
 */
public class EnterData extends MeigicActivity<EnterDataPresenter, EnterDataView> implements EnterDataView {

    @BindView(R.id.total_topics) EditText totalTopics;
    @BindView(R.id.taken_topics) EditText takenTopics;
    @BindView(R.id.studied_topics) EditText studiedTopics;
    @BindView(R.id.calculate) Button calculate;

    @Override protected EnterDataPresenter initPresenter() {
        return new EnterDataPresenter(this);
    }

    @Override protected void initView() {
        setContentView(R.layout.activity_data);
        ButterKnife.bind(this);

        studiedTopics.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    calculate.performClick();
                }
                return true;
            }
        });

        totalTopics.addTextChangedListener(this.onTextChange());
        takenTopics.addTextChangedListener(this.onTextChange());
        studiedTopics.addTextChangedListener(this.onTextChange());
    }

    @OnClick(R.id.calculate) public void onCalculateButtonClick() {
        presenter.saveUserInput(totalTopics.getText(), takenTopics.getText(), studiedTopics.getText());
    }

    @Override
    public void setButtonEnable() {
        calculate.setEnabled(!TextUtils.isEmpty(totalTopics.getText())
                && !TextUtils.isEmpty(takenTopics.getText())
                && !TextUtils.isEmpty(studiedTopics.getText()));
    }

    @Override
    public void changeButtonColor() {
        if(calculate.isEnabled()){
            calculate.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        } else {
            calculate.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryNotEnabled));
        }
    }

    @Override
    public void showResults() {
        Intent i = new Intent(this, ResultActivity.class);
        startActivity(i);
    }

    @Override
    public void changeTotalTopics(String total) {
        totalTopics.setText(total);
    }

    @Override
    public void changeTakenTopics(String taken) {
        takenTopics.setText(taken);
    }

    @Override
    public void changeStudiedTopics(String studied) {
        studiedTopics.setText(studied);
    }

    private TextWatcher onTextChange() {
        return new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.refreshButton();
            }

            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override public void afterTextChanged(Editable editable) {}
        };
    }
}
