package team.cake.theredalliance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TeamViewer extends AppCompatActivity {
    private final String TAG = "TEAMVIEWER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_viewer);
        TextView number = findViewById(R.id.number);
        Intent intent = getIntent();
        String teamNumber = intent.getStringExtra("NUMBER");
        number.setText(teamNumber);
        SharedPreferences sharePref = this.getSharedPreferences("Teams_List", Context.MODE_PRIVATE);
        String teamName = sharePref.getString(teamNumber, "Sorry Not Found");
        TextView name = findViewById(R.id.teamName);
        name.setText(teamName);
        System.out.println(sharePref.getAll());
    }
}