package team.cake.theredalliance;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Scanner;

public class ConfigReader {
    String _filePath;
    String _entries[];
    int _numEntires;
    private WeakReference<Activity> mee;
    ConfigReader(Activity pass, String filePath) {
        mee = new WeakReference<>(pass);
        _filePath = filePath;
        _numEntires = 0;
    }
    public void start() {
        File file = new File(_filePath);
        try {
            Log.d("Config_Reader", "About to read config file");
            FileReader fileReader = new FileReader(_filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            do {
                _entries[_numEntires++] = reader.readLine();
            }
            while (_entries[_numEntires - 1] != null);
            reader.close();
            fileReader.close();
            Log.d("Config_Reader", "Done Reading config file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Config_Reader", "File: " + _filePath);

        mee.get().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView output = mee.get().findViewById(R.id.outPutConfig);
                String text = "Entries: " + _numEntires + "\n";
                for (int i = 0; i < _numEntires; i++) {
                    text += _entries[i] + "\n";
                }
                output.setText(text);
                Log.d("SET_TEXT", text);
            }
        });
    }
}
