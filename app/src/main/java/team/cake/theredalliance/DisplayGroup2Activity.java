package team.cake.theredalliance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DisplayGroup2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_group2);
    }
    public void sendToJson(View view){
        EditText nameView = findViewById(R.id.JsonName);
        Json json = new Json();
        String nametext = nameView.getText().toString();
        SharedPreferences sprefs = getPreferences(MODE_PRIVATE);
        json.writeJsonSharedPref(nametext, "file.json", sprefs);
    }
    public void pullFromJson(View view){
        Json json = new Json();
        SharedPreferences sprefs = getPreferences(MODE_PRIVATE);
        String content = json.readJsonSharedPref("file.json", sprefs);
        EditText name = findViewById(R.id.JsonName);
        name.setText(content + content);
    }
}