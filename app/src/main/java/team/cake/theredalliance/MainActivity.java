package team.cake.theredalliance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, TeamsList.class);
        startActivity(intent);
    }
    public void group1DevPage(View view) {
        Intent intent = new Intent(this, DisplayGroup1Activity.class);
        startActivity(intent);
    }
    public void group2DevPage(View view) {
        Intent intent = new Intent(this, DisplayGroup2Activity.class);
        startActivity(intent);
    }

    public void TeamsListPage(View view) {
        Intent intent = new Intent(this, TeamsList.class);
        startActivity(intent);
    }
}