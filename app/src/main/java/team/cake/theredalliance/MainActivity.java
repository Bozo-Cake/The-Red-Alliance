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
    }
    public void survey(View view) {
        Intent intent = new Intent(this, DisplayGroup1Activity.class);
        startActivity(intent);
    }
    public void interview(View view) {
        Intent intent = new Intent(this, DisplayGroup2Activity.class);
        startActivity(intent);
    }
    public void matches(View view) {
        Intent intent = new Intent(this, DisplayGroup1Activity.class);
        startActivity(intent);
    }
    public void teams(View view) {
        Intent intent = new Intent(this, DisplayGroup2Activity.class);
        startActivity(intent);
    }
    public void main(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}