package team.cake.theredalliance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class InterviewActivity extends AppCompatActivity {
    SharedPreferences _privateSP;
    Json _json;
    Map<String, ?> _keys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);
        _privateSP = getPreferences(MODE_PRIVATE);
        _json = new Json();
        _keys = _privateSP.getAll();
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
        EditText teamNameElement = findViewById(R.id.TeamName);
        String teamNametext = teamNameElement.getText().toString().toLowerCase();
        EditText robotNameElement = findViewById(R.id.RobotName);
        String robotNametext = robotNameElement.getText().toString();
        //TODO We need to make sure we capture all of the UI elements for the interview.

        interview inter = new interview(teamNametext, robotNametext, 200, 96);
        _json.saveInterview(inter, teamNametext, _privateSP);
        Log.wtf("group2","WTF I saved your interview!");
        //Toast letting the user know the team was saved.
        Toast.makeText(getApplicationContext(),"Team Saved",Toast.LENGTH_SHORT).show();

        teamNameElement.setText("");
        robotNameElement.setText("");
    }
    public void getInterview(View view){
        EditText teamNameElement = findViewById(R.id.LoadTeamName);
        String teamNametext = teamNameElement.getText().toString().toLowerCase();

        interview inter = _json.readInterview(teamNametext, _privateSP);
        EditText teamName = findViewById(R.id.TeamName);
        //TODO make sure inter is not null before trying to set anything.
        teamName.setText(inter._team);
        EditText robotName = findViewById(R.id.RobotName);
        robotName.setText(inter._robot);
        teamNameElement.setText("");
    }
}