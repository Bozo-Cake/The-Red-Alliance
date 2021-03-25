package team.cake.theredalliance;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.LinearLayout;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class interview implements Runnable {
    Uri _uri;
    List<Field> _questions = null;
    final String TAG = "Interview";
    private WeakReference<Activity> mee;
    ConfigReader _configReader;
    interview(Activity pass, Uri uri) {
        mee = new WeakReference<>(pass);
        _uri = uri;
        _configReader = new ConfigReader(pass, uri);
    }
    public void run(){
        try {
            Log.d("TAG", "About to read config file");
            _configReader.readTextFromUri();
            Log.d("TAG", "Done Reading config file");
        } catch (IOException e) {e.printStackTrace();}

        _questions = new ArrayList<>();
        for (int i = 0; i < _configReader._numbEntries; i++) {
            //Parse each object
            _questions.add(_configReader.parseEntryIntoObject(_configReader._entries.get(i)));
            int finalI = i;
            mee.get().runOnUiThread(() -> {
                LinearLayout layout =  mee.get().findViewById(R.id.layout);
                _questions.get(finalI).setActivity(mee);
                int id = _questions.get(finalI).makeView(layout);
            });
        }
    }
    //https://developer.android.com/training/data-storage/shared/documents-files#open
}
