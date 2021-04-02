package team.cake.theredalliance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ConfigMenu extends AppCompatActivity {
    private final int MATCH = 0;
    private final int INTERVIEW = 1;
    private final int LIST = 2;
    SharedPreferences _prefs;
    SharedPreferences _teamPrefs;
    String _referral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_menu);
        _prefs = getSharedPreferences("Config_Files", 0);
        _teamPrefs = getSharedPreferences("Teams_List", 0);

        Intent intent = getIntent();
        _referral = intent.getStringExtra("FROM");
        if(_referral != null) {
            handleReferences(false);
        }
    }

    private void handleReferences(boolean referenceReturn) {
        Intent intent;
        boolean success = false;
        switch (_referral) {
            case "MATCH": {
                getConfigFile(MATCH);
                intent = new Intent(this, SurveyActivity.class);
                break;
            }
            case "INTERVIEW": {
                getConfigFile(INTERVIEW);
                intent = new Intent(this, InterviewActivity.class);
                break;
            }
            case "LIST": {
                getConfigFile(LIST);
                intent = new Intent(this, TeamActivity.class);
                break;
            }
            default: {
                return;
            }
        }
        if(referenceReturn) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    public void loadMatchCSV(View view) {
        getConfigFile(MATCH);
    }
    public void clearMatchPrefs(View view) {
        clearPrefs(MATCH);
    }
    public void loadInterviewCSV(View view) {
        getConfigFile(INTERVIEW);
    }
    public void clearInterviewPrefs(View view) {
        clearPrefs(INTERVIEW);
    }
    public void loadTeamsListCSV(View view) {
        getConfigFile(LIST);
    }
    public void clearTeamsListPrefs(View view) {
        clearPrefs(LIST);
    }

    private void getConfigFile(int code) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");//text/csv
        startActivityForResult(intent, code);
    }

    private void clearPrefs(int code) {
        switch (code) {
            case MATCH: {
                _prefs.edit().remove("Survey_Questions").apply();
                break;
            }
            case INTERVIEW: {
                _prefs.edit().remove("Interview_Questions").apply();
                break;
            }
            case LIST: {
                _prefs.edit().remove("Teams_List").apply();
                break;
            }
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("data", data.toString());
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if (uri == null) {
                Log.d("FIND_PATH","Uri is null");
            }
            else {
                String _key = null;
                switch (requestCode) {
                    case MATCH: {
                        _key = "Survey_Questions";
                        break;
                    }
                    case INTERVIEW: {
                        _key = "Interview_Questions";
                        break;
                    }
                    case LIST: {
                        _key = "Teams_List";
                        break;
                    }
                    default: {
                        //
                    }
                }
                ConfigReader configReader = new ConfigReader(this, uri, _key);
                configReader.storeConfig();
                if(requestCode == LIST){
                    convertList();
                }
                if(_referral != null) {
                    handleReferences(true);
                }
            }
        }
    }

    private void convertList() {
        Set<String> teamNames = _prefs.getStringSet("Teams_List", null);
        if (teamNames == null){
            Log.e("ConfigMenu","Interprited Team Menu Missing");
            return;
        }
        for (String teamName : teamNames) {
            String[] lines = teamName.split(",");
            System.out.println(Arrays.toString(lines));
            _teamPrefs.edit().putString(lines[0], lines[1]).apply();
        }
        _teamPrefs.edit().apply();
        Map<String, ?> map = _teamPrefs.getAll();
        Log.d("ConfigMenu", String.valueOf(map.size()));
    }
}