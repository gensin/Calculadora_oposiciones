package es.pau.calculadoraoposiciones;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import static utils.CalculationUtils.probabilityPercentage;

public class ResultActivity extends AppCompatActivity implements TextWatcher {

    private int total, taken, studied;
    private float percentage;

    private ProgressBar calculationProgress;
    private TextView tvPercentage;
    private EditText newTopics;
    private Button recalculate;

    CalculatePercentageAsyncTask calculationAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        total = getIntent().getIntExtra(DataActivity.TOTAL_SAVED,0);
        taken = getIntent().getIntExtra(DataActivity.TAKEN_SAVED,0);
        studied = getIntent().getIntExtra(DataActivity.STUDIED_SAVED,0);

        calculationProgress = (ProgressBar) findViewById(R.id.calculate_progress);
        tvPercentage = (TextView) findViewById(R.id.percentage);
        newTopics = (EditText) this.findViewById(R.id.new_topics);
        newTopics.addTextChangedListener(this);
        recalculate = (Button) findViewById(R.id.recalculate);
        recalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer newTopicsNumber = Integer.valueOf(newTopics.getText().toString());
                recalculate(newTopicsNumber);
            }
        });
        SharedPreferences prefs = getSharedPreferences(DataActivity.SHARED_PREFERENCES, Context.MODE_PRIVATE);

        if(total==0 || taken == 0 || studied == 0){
            //TODO:Lanzar mensaje de aviso
            onBackPressed();
        } else if(prefs.contains(DataActivity.RESULT_SAVED)) {
            float savedPercentage = prefs.getFloat(DataActivity.RESULT_SAVED, (float) 0);
            writePercentage(savedPercentage);
        } else {
            calculate();
        }
        setButtonEnable();
    }

    private void setButtonEnable(){
        recalculate.setEnabled(!TextUtils.isEmpty(newTopics.getText()));
    }

    private void recalculate(int newStudied){
        studied = newStudied;
        calculate();
    }

    private void calculate(){
        calculationAsyncTask = new CalculatePercentageAsyncTask();
        calculationAsyncTask.execute(total, taken, studied);
    }

    private void writePercentage(Float percentage){
        SharedPreferences prefs = getSharedPreferences(DataActivity.SHARED_PREFERENCES, Context.MODE_PRIVATE);

        calculationProgress.setVisibility(View.INVISIBLE);
        tvPercentage.setVisibility(View.VISIBLE);
        String savedPercentageString = String.format("%.2f",percentage);
        tvPercentage.setText(savedPercentageString);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(DataActivity.RESULT_SAVED,percentage);
        editor.apply();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        setButtonEnable();
    }

    private class CalculatePercentageAsyncTask extends AsyncTask<Integer, Void, Float> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvPercentage.setVisibility(View.INVISIBLE);
            calculationProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected Float doInBackground(Integer... userInfo) {
            return (float) probabilityPercentage(userInfo[0],userInfo[1],userInfo[2]);
        }

        @Override
        protected void onPostExecute(Float resultPercentage) {
            super.onPostExecute(resultPercentage);
            writePercentage(resultPercentage);
        }
    }
}
