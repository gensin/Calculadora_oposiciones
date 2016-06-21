package es.pau.calculadoraoposiciones.features.enterData;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
 * Created by pau on 22/06/16.
 */
// TODO: 22/06/16 Crear editor de preferencias
public class EnterData extends MeigicActivity<EnterDataPresenter, EnterDataView> implements EnterDataView {

    public static final String SHARED_PREFERENCES = "shared preferences";
    public static final String TOTAL_SAVED = "total topics saved";
    public static final String TAKEN_SAVED = "taken topics saved";
    public static final String STUDIED_SAVED = "studied topics saved";
    public static final String RESULT_SAVED = "percentage resulted saved";

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

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);

        if(prefs.contains(TOTAL_SAVED)){
            totalTopics.setText(Integer.toString(prefs.getInt(TOTAL_SAVED,0)));
        }

        if(prefs.contains(TAKEN_SAVED)){
            takenTopics.setText(Integer.toString(prefs.getInt(TAKEN_SAVED,0)));
        }

        if(prefs.contains(STUDIED_SAVED)){
            studiedTopics.setText(Integer.toString(prefs.getInt(STUDIED_SAVED,0)));
        }
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

        setButtonEnable();
        changeButtonColor();
    }

    @OnClick(R.id.calculate) public void onCalculateButtonClick() {
        //Add user input to SharedPreferences
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(TOTAL_SAVED, Integer.parseInt(totalTopics.getText().toString()));
        editor.putInt(TAKEN_SAVED, Integer.parseInt(takenTopics.getText().toString()));
        editor.putInt(STUDIED_SAVED, Integer.parseInt(studiedTopics.getText().toString()));
        editor.remove(RESULT_SAVED);
        editor.apply();

        //Call to result activity
        Intent i = new Intent(this, ResultActivity.class);
        i.putExtra(TOTAL_SAVED, Integer.valueOf(totalTopics.getText().toString()));
        i.putExtra(TAKEN_SAVED, Integer.valueOf(takenTopics.getText().toString()));
        i.putExtra(STUDIED_SAVED, Integer.valueOf(studiedTopics.getText().toString()));
        startActivity(i);
    }

    private void setButtonEnable() {
        calculate.setEnabled(!TextUtils.isEmpty(totalTopics.getText())
                && !TextUtils.isEmpty(takenTopics.getText())
                && !TextUtils.isEmpty(studiedTopics.getText()));
    }

    private void changeButtonColor() {
        if(calculate.isEnabled()){
            calculate.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        } else {
            calculate.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryNotEnabled));
        }
    }

    private TextWatcher onTextChange() {
        return new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setButtonEnable();
                changeButtonColor();
            }

            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override public void afterTextChanged(Editable editable) {}
        };
    }
}
