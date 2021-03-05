package team.cake.theredalliance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class DisplayGroup1Activity extends AppCompatActivity {
    int FILE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_group1);
    }

    public void getConfigFile(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");//text/csv
        startActivityForResult(intent, FILE_REQUEST);
    }
    //@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("data", data.toString());
        if (requestCode == FILE_REQUEST && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if (uri == null) {
                Log.d("FIND_PATH","Uri is null");
            }
            else {
                ConfigReader configReader = new ConfigReader(this, uri);
                Thread thread = new Thread(configReader);
                thread.start();
            }
        }
    }

    public void viewSurveyPage(View view) {
        Intent intent = new Intent(this, SurveyActivity.class);
        startActivity(intent);
    }
}