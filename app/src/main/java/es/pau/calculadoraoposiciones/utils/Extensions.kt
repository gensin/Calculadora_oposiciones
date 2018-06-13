package es.pau.calculadoraoposiciones.utils

import android.content.Context
import android.opengl.Visibility
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import es.pau.calculadoraoposiciones.features.enterData.DataActivity
import java.util.*
import java.util.concurrent.ThreadLocalRandom

/**
 * Created on 12/06/18.
 */

// Views
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun EditText.getTextAsInt(): Int{
    try {
        return Integer.parseInt(text.toString())
    } catch (e: NumberFormatException){
        return 0
    }
}

fun EditText.isEmpty(): Boolean {
    return TextUtils.isEmpty(text)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun ProgressBar.hide() {
    this.visibility = View.GONE
}

fun ProgressBar.show() {
    this.visibility = View.VISIBLE
}

// Maths
fun ClosedRange<Int>.random() =
        Random().nextInt(1 + endInclusive - start) +  start