package es.pau.calculadoraoposiciones.utils

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText
import es.pau.calculadoraoposiciones.features.enterData.DataActivity

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