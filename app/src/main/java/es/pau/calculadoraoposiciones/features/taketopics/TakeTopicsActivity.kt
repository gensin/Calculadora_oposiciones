package es.pau.calculadoraoposiciones.features.taketopics

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
import es.pau.calculadoraoposiciones.R
import es.pau.calculadoraoposiciones.features.prefs
import es.pau.calculadoraoposiciones.utils.getTextAsInt
import es.pau.calculadoraoposiciones.utils.hideKeyboard
import es.pau.calculadoraoposiciones.utils.takeRandomTopics
import kotlinx.android.synthetic.main.activity_take_topics.*
import kotlinx.android.synthetic.main.content_take_topics.*


class TakeTopicsActivity : AppCompatActivity() {

    lateinit var topicsListAdapter: TopicListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_topics)

        sendTopics.setOnClickListener {
           //TODO("Enviar mail con topics")
        }

        newTaken.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                takeAgain.performClick()
            }
            true
        }

        initiliazeList()

        takeAgain.setOnClickListener{
            newTaken.hideKeyboard()
            if (newTaken.text.isNotEmpty()) {
                prefs.takenPref = newTaken.getTextAsInt()
            }
            createList()
        }

        newTaken.setText(prefs.takenPref.toString())
        takeAgain.performClick()
        newTaken.hideKeyboard()
        newTaken.clearFocus()
    }

    private fun initiliazeList() {
        topicsTaken.layoutManager = LinearLayoutManager(this)
        val decoration = SeparatorDecoration(this, Color.TRANSPARENT, 8f)
        topicsTaken.addItemDecoration(decoration)
    }

    private fun createList() {
        this.topicsListAdapter = TopicListAdapter(this, takeRandomTopics(prefs.takenPref, prefs.totalPref))
        topicsTaken.adapter = this.topicsListAdapter
    }

}
