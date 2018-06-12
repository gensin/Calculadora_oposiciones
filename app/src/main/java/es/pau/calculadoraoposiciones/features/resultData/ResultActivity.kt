package es.pau.calculadoraoposiciones.features.resultData

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo

import es.pau.calculadoraoposiciones.R
import es.pau.calculadoraoposiciones.features.enterData.DataActivity
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import utils.*

class ResultActivity : AppCompatActivity() {

    private val total: Int = intent.getIntExtra(DataActivity.TOTAL_SAVED, 0)
    private val taken: Int = intent.getIntExtra(DataActivity.TAKEN_SAVED, 0)
    private val studied: Int = intent.getIntExtra(DataActivity.STUDIED_SAVED, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        recalculate.setOnClickListener {
            val newTopicsNumber = Integer.valueOf(newTopics.text.toString())
            calculate(newTopicsNumber)
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

        } else if (containsPreference(DataActivity.RESULT_SAVED)) {
            writePercentage(getStringPreference(DataActivity.RESULT_SAVED))

        } else {
            calculate(studied)
        }

        setButtonEnable()
    }

    private fun setButtonEnable() {
        recalculate.isEnabled = !TextUtils.isEmpty(newTopics.text)
    }


    private fun calculate(studied: Int) {
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

        putStringPreference(DataActivity.RESULT_SAVED, percentageText)
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
