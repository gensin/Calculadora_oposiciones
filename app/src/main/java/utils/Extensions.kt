package utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import es.pau.calculadoraoposiciones.features.enterData.DataActivity

/**
 * Created on 12/06/18.
 */
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

// Preferences
fun Context.containsPreference(key: String): Boolean {
    val prefs = getSharedPreferences(DataActivity.SHARED_PREFERENCES, Context.MODE_PRIVATE)
    return prefs.contains(key)
}

fun Context.getStringPreference(key: String): String {
    val prefs = getSharedPreferences(DataActivity.SHARED_PREFERENCES, Context.MODE_PRIVATE)
    return prefs.getString(key,"")
}

fun Context.putStringPreference(key: String, data: String) {
    val prefs = getSharedPreferences(DataActivity.SHARED_PREFERENCES, Context.MODE_PRIVATE)
    prefs.edit().putString(key, data).apply()
}