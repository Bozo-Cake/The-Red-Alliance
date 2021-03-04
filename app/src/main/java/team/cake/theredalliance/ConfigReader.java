package team.cake.theredalliance;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfigReader {//ToDo: Move to separate thread, currently running on UI Thread?
    Uri _uri;
    List<String> _entries = new ArrayList<>();
    int _numbEntries;
    final String TAG = "Config_Reader";
    private WeakReference<Activity> mee;
    ConfigReader(Activity pass, Uri uri) {
        mee = new WeakReference<>(pass);
        _uri = uri;
        _numbEntries = 0;
    }
    public void start() {
        try {
            Log.d("TAG", "About to read config file");
            readTextFromUri();
            Log.d("TAG", "Done Reading config file");
        } catch (IOException e) {e.printStackTrace();}

        String text = "Entries: " + _numbEntries + "\n";
        for (int i = 0; i < _numbEntries; i++) { //Don't do this on UI thread.
            text += _entries.get(i) + "\n";
        }
        final String contents = text;
        mee.get().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView output = mee.get().findViewById(R.id.outPutConfig);
                output.setText(contents);
                Log.d("SET_TEXT", contents);
            }
        });
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
}
