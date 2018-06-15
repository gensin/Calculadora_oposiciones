package es.pau.calculadoraoposiciones.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created on 13/06/18.
 *
 * Based on the very clever solution of Ben Deitch from http://blog.teamtreehouse.com/making-sharedpreferences-easy-with-kotlin
 */
class Preferences (context: Context) {
    private val PREFS_FILENAME = "es.pau.calculadoraoposiciones.prefs"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);

    fun remove(key: String) = prefs.edit().remove(key).apply()

    fun contains(key: String) = prefs.contains(key)

    // App Preferences

    val TOTAL_SAVED = "total topics saved"
    var totalPref: Int
        get() = prefs.getInt(TOTAL_SAVED, 0)
        set(value) = prefs.edit().putInt(TOTAL_SAVED, value).apply()

    val TAKEN_SAVED = "taken topics saved"
    var takenPref: Int
        get() = prefs.getInt(TAKEN_SAVED, 0)
        set(value) = prefs.edit().putInt(TAKEN_SAVED, value).apply()

    val STUDIED_SAVED = "studied topics saved"
    var studiedPref: Int
        get() = prefs.getInt(STUDIED_SAVED, 0)
        set(value) = prefs.edit().putInt(STUDIED_SAVED, value).apply()

    val RESULT_SAVED = "percentage resulted saved"
    var resultPref: String
        get() = prefs.getString(RESULT_SAVED, "")
        set(value) = prefs.edit().putString(RESULT_SAVED, value).apply()
}