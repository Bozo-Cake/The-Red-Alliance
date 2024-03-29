package team.cake.theredalliance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout2);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void MatchReport(View view) {
        Intent intent = new Intent(this, SurveyActivity.class);
        startActivity(intent);
    }
    public void interview(View view) {
        Intent intent = new Intent(this, InterviewActivity.class);
        startActivity(intent);
    }
    public void settings(View view) {
        Intent intent = new Intent(this, ConfigMenu.class);
        startActivity(intent);
    }
    public void teams(View view) {
        Intent intent = new Intent(this, TeamActivity.class);
        startActivity(intent);
    }
    public void main(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void MatchReport(MenuItem view) {
        Intent intent = new Intent(this, SurveyActivity.class);
        startActivity(intent);
    }
    public void interview(MenuItem view) {
        Intent intent = new Intent(this, InterviewActivity.class);
        startActivity(intent);
    }
    public void settings(MenuItem view) {
        Intent intent = new Intent(this, ConfigMenu.class);
        startActivity(intent);
    }
    public void teams(MenuItem view) {
        Intent intent = new Intent(this, TeamActivity.class);
        startActivity(intent);
    }
    public void main(MenuItem view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}