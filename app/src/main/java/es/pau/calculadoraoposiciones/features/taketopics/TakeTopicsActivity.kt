package es.pau.calculadoraoposiciones.features.taketopics

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import es.pau.calculadoraoposiciones.R

import kotlinx.android.synthetic.main.activity_take_topics.*

class TakeTopicsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_topics)
        setSupportActionBar(toolbar)

        sendTopics.setOnClickListener { view ->
           //TODO("Enviar mail con topics")
        }
    }

}
