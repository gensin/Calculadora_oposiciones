package es.pau.calculadoraoposiciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private int total, taken, studied;
    private double percentage;

    private EditText newTopics;
    private Button recalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        total = getIntent().getIntExtra(DataActivity.TOTAL_SAVED,0);
        taken = getIntent().getIntExtra(DataActivity.TAKEN_SAVED,0);
        studied = getIntent().getIntExtra(DataActivity.STUDIED_SAVED,0);

        if(total==0 || taken == 0 || studied == 0){
            //Lanzar mensaje de aviso
            onBackPressed();
        } else {
            //Lanzar asyntask para averiguar el resultado de la operación.
            TextView percentage = (TextView) findViewById(R.id.percentage);

            setButtonEnable();
        }
    }

    private void setButtonEnable(){
        recalculate.setEnabled(!TextUtils.isEmpty(newTopics.getText()));
    }

    private void recalculate(int newStudied){
        //Hacer la recalculación con los nuevos temas
    }
}
