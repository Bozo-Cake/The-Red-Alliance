package team.cake.theredalliance;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.common.base.Splitter;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ConfigReader implements Runnable {//ToDo: Move to separate thread, currently running on UI Thread?
    Uri _uri;
    final String TAG = "Config_Reader";
    private WeakReference<Activity> mee;
    private String _key;
    private Set<String> contents;
    ConfigReader(Activity pass, Uri uri, String PrefsKey) {
        mee = new WeakReference<>(pass);
        _uri = uri;
        _key = PrefsKey;
    }
    public void run() {
        try {
            Log.d("TAG", "About to read config file");
            readTextFromUri();
            Log.d("TAG", "Done Reading config file");
        } catch (IOException e) {e.printStackTrace();}
        
        //save GSON object to prefs
        SharedPreferences sharedPref = mee.get().getSharedPreferences("Config_Files", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        try {
            readTextFromUri();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(contents != null) {
            editor.putStringSet(_key, contents);
            editor.apply();
            Log.d(TAG, "Saved Into Prefs");
        }
        else {
            Log.e("Config_Reader", "ConfigFile string length zero, unable to save to prefs.");
        }

    }

    //https://developer.android.com/training/data-storage/shared/documents-files#open
    private void readTextFromUri() throws IOException {
        try (InputStream inputStream =
                     mee.get().getContentResolver().openInputStream(_uri);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contents.add(line);
                Log.d(TAG, line);
            }

        }
    }
    
}
