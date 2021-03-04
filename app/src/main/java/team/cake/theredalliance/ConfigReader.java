package team.cake.theredalliance;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ConfigReader {//ToDo: Move to separate thread, currently running on UI Thread?
    String _filePath;
    List<String> _entries = new ArrayList<>();
    int _numbEntries;
    final String TAG = "Config_Reader";
    private WeakReference<Activity> mee;
    ConfigReader(Activity pass, String filePath) {
        mee = new WeakReference<>(pass);
        _filePath = filePath;
        _numbEntries = 0;
    }
    public void start() {
        try {
            Log.d("TAG", "About to read config file");
            FileReader fileReader = new FileReader(_filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            do {
                line = reader.readLine();

                _entries.add(line);
                if(line == null && _numbEntries == 0) {
                    Log.e(TAG, _filePath + " is empty!");
                    break;
                }
                //Log.d(TAG, line);
                _numbEntries++;
            }
            while (line != null);
            reader.close();
            fileReader.close();
            Log.d("TAG", "Done Reading config file");
        } catch (IOException e) {e.printStackTrace();}
        //catch(FileNotFoundException e) {Log.e(TAG, "File not found: " + _filePath);
        Log.d("TAG", "File: " + _filePath);


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
}
