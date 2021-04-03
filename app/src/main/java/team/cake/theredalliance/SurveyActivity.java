package team.cake.theredalliance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Set;

public class SurveyActivity extends AppCompatActivity {
    private static final int FILE_REQUEST = 1;
    private SurveyQuestionParser _parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        GenerateSurvey();
    }

    private void GenerateSurvey() {
        SharedPreferences sharedPref = this.getSharedPreferences("Config_Files", Context.MODE_PRIVATE);
        Set<String> questions = sharedPref.getStringSet("Survey_Questions", null);
        if (questions == null) {
            Toast.makeText(this, "No Saved Match Survey Config File, Please Load one", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConfigMenu.class);
            intent.putExtra("FROM", "MATCH");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else {
            LinearLayout survey = findViewById(R.id.MatchSurvey);
            _parser = new SurveyQuestionParser(this, survey, questions);
        }
    }

    public void save(View view) {
        EditText editText = findViewById(R.id.teamNumberField);
        String key = editText.getText().toString();
        SharedPreferences sharedPref = this.getSharedPreferences("Match_Results", Context.MODE_PRIVATE);
        _parser.saveEverything(key, sharedPref);
    }
    public void clear(View view) {
        Intent intent = new Intent(this, SurveyActivity.class);
        startActivity(intent);
    }

    public void MatchReport(MenuItem view) {
        Intent intent = new Intent(this, SurveyActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void interview(MenuItem view) {
        Intent intent = new Intent(this, InterviewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void settings(MenuItem view) {
        Intent intent = new Intent(this, ConfigMenu.class);
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
}