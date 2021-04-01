package team.cake.theredalliance;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.lang.ref.WeakReference;
import java.util.Map;

public abstract class Field implements Askable{
    String _name;
    SurveyQuestionsType _type;
    Map<String,String> _map;
    WeakReference<Activity> _activity;
    String _data;
    int _id;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    Field(Map<String,String> map) {
        _name = map.get("name");
        _type = SurveyQuestionsType.valueOf(map.get("type"));
        _map = map;
        _id = View.generateViewId();
        //ToDo: throw exception in any Field object constructor for ConfigReader to disregard faulty file
    }

    protected LinearLayout generateContainer() {
        //Generate view container
        LinearLayout view = new LinearLayout(_activity.get());
        view.setTag(_map.get("name"));
        //Id's aren't particularly needed.

        //Add Question Name
        TextView textView = new TextView(_activity.get());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setText(_name);
        view.addView(textView);

        return view;
    }

    String getName() { return _name; }
    SurveyQuestionsType getType() { return _type; }
    Map<String,String> getConfig() { return _map; }

    public void setActivity(WeakReference<Activity> activity){ _activity = activity; }
    public void makeView(ViewGroup layout, String data) {_data = data; makeView(layout);}
}
