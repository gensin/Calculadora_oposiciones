package es.pau.calculadoraoposiciones;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Project: Calculadoradeprobabilidaddeoposiciones
 * Created on 22/06/16.
 */
public class Preferences {

    private static String SHARED_PREFERENCES = "shared preferences";

    public static final String TOTAL_SAVED = "total topics saved";
    public static final String TAKEN_SAVED = "taken topics saved";
    public static final String STUDIED_SAVED = "studied topics saved";
    public static final String RESULT_SAVED = "percentage resulted saved";

    private static SharedPreferences sharedPreferences;

    public static void register(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static boolean loadBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public static void saveBoolean(String key, Boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static int loadInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public static void saveInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public static String loadString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public static void saveString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public static void clear() {
        sharedPreferences.edit().clear().apply();
    }

    public static void delete(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public static boolean hasPreference(String key) {
        return sharedPreferences.contains(key);
    }
}
