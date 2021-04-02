package team.cake.theredalliance;

import android.app.Activity;
import android.content.Context;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayout;
import com.google.common.base.Splitter;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TeamActivity extends AppCompatActivity {
    private final int FILE_REQUEST = 3;
    private final String KEY = "Teams_List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        loadTeams();
    }
    private void loadTeams() {
        SharedPreferences teamPref = this.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        //sharedPref.edit().clear().commit();
        Set<String> teamNames = teamPref.getStringSet(KEY, null);

        if (teamNames == null) {
            Toast.makeText(this, "No Saved Team Config File, Please Load one", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConfigMenu.class);
            intent.putExtra("FROM", "LIST");
            startActivity(intent);
        } else {
            //Iterator<String> it = teamNames.iterator();
            FlexboxLayout flexboxLayout = (FlexboxLayout) findViewById(R.id.flexbox_layout);
            flexboxLayout.setFlexDirection(FlexDirection.ROW);
            Map<String, ?> map = teamPref.getAll();
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                View icon = getLayoutInflater().inflate(R.layout.team_icon_button, null);
                TextView teamNumber = icon.findViewById(R.id.teamNumber);
                Log.d("HELP", teamNumber.getText().toString());
                //ToDo: set number from list
                teamNumber.setText(entry.getKey());
                //ToDo: set id same as number
                icon.setId(Integer.parseInt(entry.getKey()));
                flexboxLayout.addView(icon);
            }
            //while((it != null) && it.hasNext()) {
            //    String lines[] = it.next().split(",");
            //    Log.e("Number", lines[0]);
            //    Log.e("Name", lines[1]);
            //    teamPref.edit().putString(lines[0],lines[1]);
            //    View icon = getLayoutInflater().inflate(R.layout.team_icon_button, null);
            //    TextView teamNumber = icon.findViewById(R.id.teamNumber);
            //    Log.d("HELP", teamNumber.getText().toString());
            //    //ToDo: set number from list
            //    teamNumber.setText(String.valueOf(lines[0]));
            //    //ToDo: set id same as number
            //    icon.setId(Integer.parseInt(lines[0]));
            //    flexboxLayout.addView(icon);
            //}
        }
    }
    public void MatchReport(MenuItem view) {
        Intent intent = new Intent(this, SurveyActivity .class);
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

    public void setTeamView(View view) {
        Intent intent = new Intent(this, TeamViewer.class);
        TextView number = view.findViewById(R.id.teamNumber);
        intent.putExtra("NUMBER", number.getText());
        startActivity(intent);
    }

    private void loadTeamsDemo() {
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
