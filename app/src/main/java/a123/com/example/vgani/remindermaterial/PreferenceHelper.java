package a123.com.example.vgani.remindermaterial;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {
    //SingleTon pattern
    private static PreferenceHelper mInstance;
    public static final String PREFERENCE = "preference";
    public static final String SPLASH_IS_INVISIBLE = "splash_is_invisible";

    private Context mContext;
    private SharedPreferences mPreference;

    private PreferenceHelper() {
    }

    public static PreferenceHelper getmInstance() {
        if (mInstance == null) {
            mInstance = new PreferenceHelper();
        }
        return mInstance;
    }

    public void init(Context context) {
        this.mContext = context;
        mPreference = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key) {
        return mPreference.getBoolean(key, false);
    }
}
