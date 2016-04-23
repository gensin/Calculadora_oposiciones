package es.pau.calculadoraoposiciones;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.Locale;

public class DataActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    public static final String TOTAL_TOPICS = "total topics";
    public static final String TAKEN_TOPICS = "taken topics";
    public static final String STUDIED_TOPICS = "studied topics";

    private static final String SHARED_PREFERENCES = "shared preferences";
    private static final String TOTAL_SAVED = "total topics saved";
    private static final String TAKEN_SAVED = "taken topics saved";
    private static final String STUDIED_SAVED = "studied topics saved";

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
            totalTopics.setText(Integer.toString(prefs.getInt(TOTAL_SAVED,0)));
        }

        takenTopics = (EditText) findViewById(R.id.taken_topics);
        if(prefs.contains(TAKEN_SAVED)){
            takenTopics.setText(Integer.toString(prefs.getInt(TAKEN_SAVED,0)));
        }

        studiedTopics = (EditText) findViewById(R.id.studied_topics);
        if(prefs.contains(STUDIED_SAVED)){
            studiedTopics.setText(Integer.toString(prefs.getInt(STUDIED_SAVED,0)));
        }
        studiedTopics.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
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
        editor.putInt(TAKEN_SAVED, Integer.parseInt(totalTopics.getText().toString()));
        editor.putInt(STUDIED_SAVED, Integer.parseInt(totalTopics.getText().toString()));
        editor.apply();

        //Call to result activity
        Intent i = new Intent(this, ResultActivity.class);
        i.putExtra(TOTAL_TOPICS, Integer.valueOf(totalTopics.getText().toString()));
        i.putExtra(TAKEN_TOPICS, Integer.valueOf(takenTopics.getText().toString()));
        i.putExtra(STUDIED_TOPICS, Integer.valueOf(studiedTopics.getText().toString()));
        startActivity(i);
    }

    private void setButtonEnable(){
        calculateButton.setEnabled(!TextUtils.isEmpty(totalTopics.getText())
                && !TextUtils.isEmpty(takenTopics.getText())
                && !TextUtils.isEmpty(studiedTopics.getText()));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        setButtonEnable();
    }
}
