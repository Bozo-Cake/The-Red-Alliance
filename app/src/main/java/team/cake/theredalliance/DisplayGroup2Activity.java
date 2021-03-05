package team.cake.theredalliance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Map;

public class DisplayGroup2Activity extends AppCompatActivity {
    SharedPreferences _privateSP;
    Json _json;
    Map<String, ?> _keys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_group2);
        _privateSP = getPreferences(MODE_PRIVATE);
        _json = new Json();
        _keys = _privateSP.getAll();
    }

    public void saveInterview(View view){
        EditText teamNameElement = findViewById(R.id.TeamName);
        String teamNametext = teamNameElement.getText().toString();
        EditText robotNameElement = findViewById(R.id.RobotName);
        String robotNametext = robotNameElement.getText().toString();
        //TODO We need to make sure we capture all of the UI elements for the interview.
        //TODO Make sure all shared preference keys are lowercase.
        interview inter = new interview(teamNametext, robotNametext, 200, 96);
        _json.saveInterview(inter, teamNametext, _privateSP);
        //TODO Add toast here telling user that the team was saved.
        teamNameElement.setText("");
        robotNameElement.setText("");
    }
    public void getInterview(View view){
        EditText teamNameElement = findViewById(R.id.LoadTeamName);
        String teamNametext = teamNameElement.getText().toString();
        //TODO Make sure all shared preference keys are lowercase.
        interview inter = _json.readInterview(teamNametext, _privateSP);
        EditText teamName = findViewById(R.id.TeamName);
        //TODO make sure inter is not null before trying to set anything.
        teamName.setText(inter._team);
        EditText robotName = findViewById(R.id.RobotName);
        robotName.setText(inter._robot);
        teamNameElement.setText("");
    }
}