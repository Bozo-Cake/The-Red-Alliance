package team.cake.theredalliance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TeamsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_list);
    }

    public void setTeamView(View view) {
        Intent intent = new Intent(this, TeamViewer.class);
        startActivity(intent);
    }
}