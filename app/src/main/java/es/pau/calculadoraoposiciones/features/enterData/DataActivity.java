package es.pau.calculadoraoposiciones.features.enterData;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import es.pau.calculadoraoposiciones.R;
import es.pau.calculadoraoposiciones.features.resultData.ResultActivity;

public class DataActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    public static final String SHARED_PREFERENCES = "shared preferences";
    public static final String TOTAL_SAVED = "total topics saved";
    public static final String TAKEN_SAVED = "taken topics saved";
    public static final String STUDIED_SAVED = "studied topics saved";
    public static final String RESULT_SAVED = "percentage resulted saved";

    private EditText totalTopics;
    private EditText takenTopics;
    private EditText studiedTopics;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);

        calculateButton = (Button) this.findViewById(R.id.calculate);

        totalTopics = (EditText) findViewById(R.id.total_topics);
        if(prefs.contains(TOTAL_SAVED)){
            String totalSaved = Integer.toString(prefs.getInt(TOTAL_SAVED,0));
            totalTopics.setText(totalSaved);
        }

        takenTopics = (EditText) findViewById(R.id.taken_topics);
        if(prefs.contains(TAKEN_SAVED)){
            String takenSaved = Integer.toString(prefs.getInt(TAKEN_SAVED,0));
            takenTopics.setText(takenSaved);
        }

        studiedTopics = (EditText) findViewById(R.id.studied_topics);
        if(prefs.contains(STUDIED_SAVED)){
            String studiedSaved = Integer.toString(prefs.getInt(STUDIED_SAVED,0));
            studiedTopics.setText(studiedSaved);
        }
        studiedTopics.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    calculateButton.performClick();
                }
                return true;
            }
        });

        calculateButton.setOnClickListener(this);

        totalTopics.addTextChangedListener(this);
        takenTopics.addTextChangedListener(this);
        studiedTopics.addTextChangedListener(this);

        setButtonEnable();
    }

    @Override
    public void onClick(View v) {
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

    private void setButtonEnable(){
        calculateButton.setEnabled(!TextUtils.isEmpty(totalTopics.getText())
                && !TextUtils.isEmpty(takenTopics.getText())
                && !TextUtils.isEmpty(studiedTopics.getText()));
        if(calculateButton.isEnabled()){
           calculateButton.setBackgroundColor(ContextCompat.getColor(DataActivity.this, R.color.colorPrimary));
        } else {
            calculateButton.setBackgroundColor(ContextCompat.getColor(DataActivity.this, R.color.colorPrimaryNotEnabled));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        setButtonEnable();
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}
}
