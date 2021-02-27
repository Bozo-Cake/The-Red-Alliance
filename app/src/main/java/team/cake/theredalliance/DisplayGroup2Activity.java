package team.cake.theredalliance;

import androidx.appcompat.app.AppCompatActivity;

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
        String nametext = nameView.toString();
        json.writeJson(nametext, "file.json");
    }
    public void pullFromJson(View view){
        Json json = new Json();
        String newName = json.readJson( "file.json");
        EditText name = findViewById(R.id.JsonName);
        name.setText(newName + newName);
    }
}