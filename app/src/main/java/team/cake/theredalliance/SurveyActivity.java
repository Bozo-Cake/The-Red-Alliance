package team.cake.theredalliance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Set;

public class SurveyActivity extends AppCompatActivity {
    private static final int FILE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        GenerateSurvey();
    }

    private void GenerateSurvey() {
        SharedPreferences sharedPref = this.getSharedPreferences("Config_Files", Context.MODE_PRIVATE);
        Set<String> questions = sharedPref.getStringSet("Survey_Questions", null);
        while(questions == null) {
            Toast.makeText(this, "No Saved Match Survey Config File, Please Load one", Toast.LENGTH_LONG).show();
            getConfigFile();
            questions = sharedPref.getStringSet("Config_Questions", null);
        }

        LinearLayout survey = findViewById(R.id.ScrollContainer);
        SurveyQuestionParser p = new SurveyQuestionParser(this, survey, questions);
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
    //added this
    //https://www.tutlane.com/tutorial/android/android-view-and-viewgroup-with-examples
    private void addButton() {
        ConstraintLayout layout = findViewById(R.id.SurveyLayout);
        LinearLayout survey = findViewById(R.id.ScrollContainer);
        for (int i = 0; i < 50; i++) {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < 4; j++) {
                Button btnTag = new Button(this);
                btnTag.setLayoutParams(new LinearLayout.LayoutParams
                        (LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.MATCH_PARENT));
                btnTag.setText("Button " + (j + 1 + (i * 4)));
                btnTag.setId(j + 1 + (i * 4));
                row.addView(btnTag);
            }
            survey.addView(row);
        }
        setContentView(layout);
    }
    public void getConfigFile() {
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
                ConfigReader configReader = new ConfigReader(this, uri, "Survey_Questions");
                Thread thread = new Thread(configReader);
                thread.start();
            }
        }
    }
}