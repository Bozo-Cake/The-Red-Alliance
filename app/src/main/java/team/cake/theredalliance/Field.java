package team.cake.theredalliance;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Map;

public abstract class Field {
    String _name;
    SurveyQuestionsType _type;
    Map<String,String> _map;
    WeakReference<Activity> _activity;

    Field(Map<String,String> map) {
        _name = map.get("name");
        _type = SurveyQuestionsType.valueOf(map.get("type"));
        _map = map;
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
                LinearLayout.LayoutParams.MATCH_PARENT));
        textView.setText(_name);
        view.addView(textView);

        return view;
    }

    String getName() { return _name; }
    SurveyQuestionsType getType() { return _type; }
    Map<String,String> getConfig() { return _map; }

    public void setActivity(WeakReference<Activity> activity){ _activity = activity; }
    public Integer makeView(LinearLayout layout) { return null; }
    public void saveViewData() {    }
    public void loadViewData(String data) {    }
}
