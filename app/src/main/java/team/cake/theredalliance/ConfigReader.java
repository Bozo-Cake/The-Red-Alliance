package team.cake.theredalliance;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfigReader implements Runnable {//ToDo: Move to separate thread, currently running on UI Thread?
    Uri _uri;
    List<String> _entries = new ArrayList<>();
    List<Field> _questions = null;
    int _numbEntries;
    final String TAG = "Config_Reader";
    private WeakReference<Activity> mee;
    ConfigReader(Activity pass, Uri uri) {
        mee = new WeakReference<>(pass);
        _uri = uri;
        _numbEntries = 0;
    }
    public void run() {
        try {
            Log.d("TAG", "About to read config file");
            readTextFromUri();
            Log.d("TAG", "Done Reading config file");
        } catch (IOException e) {e.printStackTrace();}

        //Test by showing contents on screen
            String text = "Entries: " + _numbEntries + "\n";
            for (int i = 0; i < _numbEntries; i++) { //Don't do this on UI thread.
                text += _entries.get(i) + "\n";
            }
            final String contents = text;
            //Suggested to replace previous with lambda
            mee.get().runOnUiThread(() -> {
                TextView output = mee.get().findViewById(R.id.outPutConfig);
                output.setText(contents);
                Log.d("SET_TEXT", contents);
            });
        //End test showing contents on screen. Move to a UnitTest?

        Gson gson = new Gson();
        _questions = new ArrayList<>();
        for (int i = 0; i < _numbEntries; i++) {
            //Parse each object
            _questions.add(parseEntry(_entries.get(i)));
        }

        //serialize to GSON
        String json = null;
        if (_questions != null) {
            json = gson.toJson(_questions);
        }

        //save GSON object to prefs
        SharedPreferences sharedPref = mee.get().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if(json.length() != 0) {
            editor.putString(mee.get().getString(R.string.Survey_Questions), json);
        }
        else {
            Log.e("Config_Reader", "Gson string lenth zero, unable to save survey to prefs.");
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
                _entries.add(line);
                _numbEntries++;
            }
        }
    }

    private Field parseEntry(String entry) {
        //Read string (single line from config file)

        //Create java object representation

        //return abstract object
        return null;
    }
}
