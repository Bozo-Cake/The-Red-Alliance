package team.cake.theredalliance;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class SurveyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Link to team page?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //ToDo: Research/Spike Palette-> (Layouts, Containers)
        addButton();//example showing buttons
        //addCheckBox();
    }
    //added this
    //https://www.tutlane.com/tutorial/android/android-view-and-viewgroup-with-examples
    private void addButton() {
        CoordinatorLayout layout = (CoordinatorLayout) findViewById(R.id.SurveyLayout);
        NestedScrollView scrollSurvey = (NestedScrollView) findViewById(R.id.NestedScroll);
        LinearLayout survey = (LinearLayout) findViewById(R.id.ScrollContainer);
        //NestedScrollView scrollSurvey = new NestedScrollView(this);
        //scrollSurvey.setLayoutParams(new LinearLayout.MarginLayoutParams(500, 500));
        //scrollSurvey.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        //LinearLayout survey = new LinearLayout(this);
        //survey.setOrientation(LinearLayout.VERTICAL);
        //survey.generateViewId(survey);
        //survey.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < 50; i++) {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < 4; j++) {
                Button btnTag = new Button(this);
                btnTag.setLayoutParams(new LinearLayout.LayoutParams
                        (LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.MATCH_PARENT));
                btnTag.setText("Button " + (j + 1 + (i * 4)));
                btnTag.setId(j + 1 + (i * 4));
                row.addView(btnTag);
            }
            survey.addView(row);
        }
        //scrollSurvey.addView(survey);
        //layout.addView(scrollSurvey);

//        if (layout.getParent() != null) {
//            ((ViewGroup) layout.getParent()).removeView(layout);
//        }
        setContentView(layout);
    }

    private void addCheckBox() {
        //
    }
}