package team.cake.theredalliance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class TeamsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_list);
        loadTeams();
    }

    public void setTeamView(View view) {
        Intent intent = new Intent(this, TeamViewer.class);
        startActivity(intent);
    }

    private void loadTeams() {
        ScrollView root = findViewById(R.id.listOfTeams);

        LinearLayout list = new LinearLayout(this);
        list.setOrientation(LinearLayout.VERTICAL);
        for (int i = 1000; i < 2000; i+=20) {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < 13; j+=3) {
                View icon = getLayoutInflater().inflate(R.layout.team_icon_button, null);
                TextView teamNumber = icon.findViewById(R.id.teamNumber);
                Log.d("HELP", teamNumber.getText().toString());
                teamNumber.setText(String.valueOf(j+i));
                icon.setId(j+i);
                row.addView(icon);
            }
            list.addView(row);
        }
        root.addView(list);
        //setContentView(root.getRootView());
    }
}