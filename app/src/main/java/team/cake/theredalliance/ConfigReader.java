package team.cake.theredalliance;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

public class ConfigReader {
    String _filePath;
    private WeakReference<Activity> mee;
    ConfigReader(Activity pass, String filePath) {
        mee = new WeakReference<>(pass);
        _filePath = filePath;
    }
    public void start() {
        File file = new File(_filePath);
        try {
            InputStream fileStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Log.d("TEST_RUN_ON_UI",_filePath);
        mee.get().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView output = mee.get().findViewById(R.id.outPutConfig);
                output.setText(_filePath);
                Log.d("SET_TEXT",_filePath);
            }
        });
    }
}
