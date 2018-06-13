package es.pau.calculadoraoposiciones.features.resultData

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo

import es.pau.calculadoraoposiciones.R
import es.pau.calculadoraoposiciones.features.enterData.DataActivity
import es.pau.calculadoraoposiciones.features.prefs
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import es.pau.calculadoraoposiciones.utils.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val total: Int = intent.getIntExtra(DataActivity.TOTAL_SAVED, 0)
        val taken: Int = intent.getIntExtra(DataActivity.TAKEN_SAVED, 0)
        val studied: Int = intent.getIntExtra(DataActivity.STUDIED_SAVED, 0)

        recalculate.setOnClickListener {
            val newTopicsNumber = Integer.valueOf(newTopics.text.toString())
            prefs.studiedPref = newTopicsNumber
            newTopics.hideKeyboard()
            calculate(total, taken, newTopicsNumber)
        }

        newTopics.afterTextChanged { setButtonEnable()  }
        newTopics.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                recalculate.performClick()
            }
            true
        }

        if (total == 0 || taken == 0 || studied == 0) {
            //TODO:Lanzar mensaje de aviso
            onBackPressed()

        } else if (prefs.contains(prefs.RESULT_SAVED)) {
            writePercentage(prefs.resultPref)

        } else {
            calculate(total, taken, studied)
        }

        setButtonEnable()
    }

    private fun setButtonEnable() {
        recalculate.isEnabled = !TextUtils.isEmpty(newTopics.text)
    }


    private fun calculate(total: Int, taken: Int, studied: Int) {
        showProgress(true)
        doAsync {
            val resultPercentage = probabilityPercentage(total, taken, studied)

            uiThread {
                writePercentage(resultPercentage)
            }
        }
    }

    private fun writePercentage(percentageText: String) {
        showProgress(false)
        percentage.text = percentageText

        prefs.resultPref = percentageText
    }

    private fun showProgress(show: Boolean){
        if (show){
            percentage.visibility = View.INVISIBLE
            calculateProgress.visibility = View.VISIBLE
        } else {
            percentage.visibility = View.VISIBLE
            calculateProgress.visibility = View.INVISIBLE
        }
    }
}
