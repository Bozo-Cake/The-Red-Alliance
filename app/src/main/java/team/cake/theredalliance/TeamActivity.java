package team.cake.theredalliance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class TeamActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
    }
    public void survey(View view) {
        Intent intent = new Intent(this, DisplayGroup1Activity.class);
        startActivity(intent);
    }
    public void interview(View view) {
        Intent intent = new Intent(this, InterviewActivity.class);
        startActivity(intent);
    }
    public void matches(View view) {
        Intent intent = new Intent(this, DisplayGroup1Activity.class);
        startActivity(intent);
    }
    public void teams(View view) {
        Intent intent = new Intent(this, TeamActivity.class);
        startActivity(intent);
    }
    public void main(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
