package es.pau.calculadoraoposiciones.features.enterData

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.EditorInfo
import es.pau.calculadoraoposiciones.R
import es.pau.calculadoraoposiciones.features.prefs
import es.pau.calculadoraoposiciones.features.resultData.ResultActivity
import es.pau.calculadoraoposiciones.features.taketopics.TakeTopicsActivity
import kotlinx.android.synthetic.main.activity_datos.*
import es.pau.calculadoraoposiciones.utils.*

class DataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos)

        initializeData()

        studiedTopics.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                calculate.performClick()
            }
            true
        }

        calculate.setOnClickListener{ onCalculateClick() }

        totalTopics.afterTextChanged { setButtonEnable() }
        takenTopics.afterTextChanged { setButtonEnable() }
        studiedTopics.afterTextChanged { setButtonEnable() }

        take.setOnClickListener {
            startActivity(Intent(this, TakeTopicsActivity::class.java))
        }

        setButtonEnable()
    }

    override fun onResume() {
        super.onResume()
        studiedTopics.setText(prefs.studiedPref.toString())
    }

    private fun initializeData() {
        if (prefs.contains(prefs.TOTAL_SAVED)) {
            totalTopics.setText(prefs.totalPref.toString())
        }

        if (prefs.contains(prefs.TAKEN_SAVED)) {
            takenTopics.setText(prefs.takenPref.toString())
        }

        if (prefs.contains(prefs.STUDIED_SAVED)) {
            studiedTopics.setText(prefs.studiedPref.toString())
        }
    }

    fun onCalculateClick() {
        //Get user input as number
        val totalNum = totalTopics.getTextAsInt()
        val takenNum = takenTopics.getTextAsInt()
        val studiedNum = studiedTopics.getTextAsInt()

        if (totalNum > 0 && takenNum > 0 && studiedNum > 0) {

            //Add user input to SharedPreferences
            prefs.totalPref = totalNum
            prefs.takenPref = takenNum
            prefs.studiedPref = studiedNum
            prefs.remove(prefs.RESULT_SAVED)

            //Call to result activity
            val i = Intent(this, ResultActivity::class.java)
            i.putExtra(TOTAL_SAVED, totalNum)
            i.putExtra(TAKEN_SAVED, takenNum)
            i.putExtra(STUDIED_SAVED, studiedNum)
            startActivity(i)
        } else {
            //TODO("mostrar error")
        }
    }

    private fun setButtonEnable() {
        calculate.isEnabled = !totalTopics.isEmpty() && !takenTopics.isEmpty() && !studiedTopics.isEmpty()
        take.isEnabled = !totalTopics.isEmpty() && !takenTopics.isEmpty()
    }

    companion object {
        val TOTAL_SAVED = "total topics saved"
        val TAKEN_SAVED = "taken topics saved"
        val STUDIED_SAVED = "studied topics saved"
    }
}
