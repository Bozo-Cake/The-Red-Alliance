package team.cake.theredalliance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class InterviewActivity extends AppCompatActivity {
    private static final int FILE_REQUEST = 2;
    Set<String> questions;
    SurveyQuestionParser _parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);
        SharedPreferences sharedPref = this.getSharedPreferences("Config_Files", Context.MODE_PRIVATE);
        questions = sharedPref.getStringSet("Interview_Questions", null);
        while(questions == null) {
            Toast.makeText(this, "No Saved Team Interview Config File, Please Load one", Toast.LENGTH_LONG).show();
            getConfigFile(null);
            questions = sharedPref.getStringSet("Interview_Questions", null);
        }
        LinearLayout survey = findViewById(R.id.InterviewContainer);
        _parser = new SurveyQuestionParser(this, survey, questions);
    }
    public void getConfigFile(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");//text/csv
        startActivityForResult(intent, FILE_REQUEST);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("data", data.toString());
        if (requestCode == FILE_REQUEST && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if (uri == null) {
                Log.d("FIND_PATH","Uri is null");
            }
            else {
                ConfigReader configReader = new ConfigReader(this, uri, "Interview_Questions");
                configReader.storeConfig();
                SharedPreferences sharedPref = this.getSharedPreferences("Config_Files", Context.MODE_PRIVATE);
                questions = sharedPref.getStringSet("Interview_Questions", null);
                LinearLayout survey = findViewById(R.id.InterviewContainer);
                _parser = new SurveyQuestionParser(this, survey, questions);
            }
        }
    }
    public void survey(MenuItem view) {
        Intent intent = new Intent(this, SurveyActivity .class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void interview(MenuItem view) {
        Intent intent = new Intent(this, InterviewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void matches(MenuItem view) {
        Intent intent = new Intent(this, MatchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void teams(MenuItem view) {
        Intent intent = new Intent(this, TeamActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void main(MenuItem view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void saveInterview(View view){
        EditText editText = findViewById(R.id.teamName);
        String key = editText.getText().toString();
        SharedPreferences sharedPref = this.getSharedPreferences("Saved_Results", Context.MODE_PRIVATE);
        _parser.saveEverything(key, sharedPref);
    }
    public void getInterview(View view){
        TextView editText = findViewById(R.id.teamName);
        String key = editText.getText().toString();
        SharedPreferences sharedPref = this.getSharedPreferences("Saved_Results", Context.MODE_PRIVATE);
        _parser.loadEverything(key, sharedPref);
    }
}