package es.pau.calculadoraoposiciones.features.resultData;

import android.content.Context;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import es.pau.calculadoraoposiciones.CalculatePercentageAsyncTask;
import es.pau.calculadoraoposiciones.R;
import es.pau.calculadoraoposiciones.features.enterData.EnterData;

public class ResultActivity extends AppCompatActivity implements TextWatcher {

    private int total, taken, studied;

    private ProgressBar calculationProgress;
    private TextView tvPercentage;
    private EditText newTopics;
    private Button recalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        total = getIntent().getIntExtra(EnterData.TOTAL_SAVED,0);
        taken = getIntent().getIntExtra(EnterData.TAKEN_SAVED,0);
        studied = getIntent().getIntExtra(EnterData.STUDIED_SAVED,0);

        calculationProgress = (ProgressBar) findViewById(R.id.calculate_progress);
        tvPercentage = (TextView) findViewById(R.id.percentage);

        newTopics = (EditText) this.findViewById(R.id.new_topics);
        recalculate = (Button) findViewById(R.id.recalculate);
        recalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer newTopicsNumber = Integer.valueOf(newTopics.getText().toString());
                recalculate(newTopicsNumber);
            }
        });
        newTopics.addTextChangedListener(this);
        newTopics.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    recalculate.performClick();
                }
                return true;
            }
        });

        SharedPreferences prefs = getSharedPreferences(EnterData.SHARED_PREFERENCES, Context.MODE_PRIVATE);

        if(total==0 || taken == 0 || studied == 0){
            //TODO:Lanzar mensaje de aviso
            onBackPressed();
        } else if(prefs.contains(EnterData.RESULT_SAVED)) {
            String savedPercentage = prefs.getString(EnterData.RESULT_SAVED,"");
            writePercentage(savedPercentage);
        } else {
            calculate();
        }
        setButtonEnable();
    }

    private void setButtonEnable(){
        recalculate.setEnabled(!TextUtils.isEmpty(newTopics.getText()));
        if(recalculate.isEnabled()){
            recalculate.setBackgroundColor(ContextCompat.getColor(ResultActivity.this, R.color.colorPrimary));
        } else {
            recalculate.setBackgroundColor(ContextCompat.getColor(ResultActivity.this, R.color.colorPrimaryNotEnabled));
        }
    }

    private void recalculate(int newStudied){
        studied = newStudied;
        calculate();
    }

    private void writePercentage(String percentage){
        SharedPreferences prefs = getSharedPreferences(EnterData.SHARED_PREFERENCES, Context.MODE_PRIVATE);

        calculationProgress.setVisibility(View.INVISIBLE);
        tvPercentage.setVisibility(View.VISIBLE);
        tvPercentage.setText(percentage);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(EnterData.RESULT_SAVED,percentage);
        editor.apply();
    }

    private void calculate(){
        CalculatePercentageAsyncTask calculationAsyncTask = new CalculatePercentageAsyncTask(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                tvPercentage.setVisibility(View.INVISIBLE);
                calculationProgress.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String resultPercentage) {
                super.onPostExecute(resultPercentage);
                writePercentage(resultPercentage);
            }
        };
        calculationAsyncTask.execute(total, taken, studied);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        setButtonEnable();
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
}
