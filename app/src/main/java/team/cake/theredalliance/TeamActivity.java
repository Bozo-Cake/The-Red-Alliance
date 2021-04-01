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
    private final String KEY = "TEAMS_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        loadTeams();
    }
    private void loadTeams() {
        SharedPreferences sharedPref = this.getSharedPreferences("Config_Files", Context.MODE_PRIVATE);
        //sharedPref.edit().clear().commit();
        Set<String> teamNames = sharedPref.getStringSet(KEY, null);

        if(teamNames == null) {
            Toast.makeText(this, "No Saved Team List Config File, Please Load one", Toast.LENGTH_LONG).show();
            getConfigFile();
        }else {
            Iterator<String> it = teamNames.iterator();
            ScrollView root = findViewById(R.id.listOfTeams);
            FlexboxLayout flexboxLayout = (FlexboxLayout) findViewById(R.id.flexbox_layout);
            flexboxLayout.setFlexDirection(FlexDirection.ROW);
            View view = flexboxLayout.getChildAt(0);
            SharedPreferences teamPref = this.getSharedPreferences("Teams_List", Context.MODE_PRIVATE);
            while((it != null) && it.hasNext()) {
                String lines[] = it.next().split(":");
                Log.e("Number", lines[0]);
                Log.e("Name", lines[1]);
                teamPref.edit().putString(lines[0],lines[1]);
                View icon = getLayoutInflater().inflate(R.layout.team_icon_button, null);
                TextView teamNumber = icon.findViewById(R.id.teamNumber);
                Log.d("HELP", teamNumber.getText().toString());
                //ToDo: set number from list
                teamNumber.setText(String.valueOf(lines[0]));
                //ToDo: set id same as number
                icon.setId(Integer.parseInt(lines[0]));
                flexboxLayout.addView(icon);
            }
            teamPref.edit().apply();
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
                ConfigReader configReader = new ConfigReader(this, uri, KEY);
                Thread thread = new Thread(configReader);
                thread.start();
            }
        }
    }
}
