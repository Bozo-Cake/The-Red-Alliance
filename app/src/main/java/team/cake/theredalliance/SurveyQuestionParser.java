package team.cake.theredalliance;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.common.base.Splitter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SurveyQuestionParser {
    private List<Field> _questions;
    private Map<String, String> _savedData;
    private Json _json;
    public SurveyQuestionParser(Activity pass, LinearLayout survey, Set<String> questions) {
        _savedData = new HashMap<>();
        _json = new Json();
        Iterator<String> it = questions.iterator();
        _questions = new ArrayList<>();
        survey.removeAllViews();
        while(it != null && it.hasNext()){
            Field q = parseEntryIntoObject(it.next());
            q.setActivity(new WeakReference<>(pass));
            String data = ""; //TODO Parse _savedData and if there is saved data display it.
            q.makeView(survey);
            _questions.add(q);

        }
        //https://stackoverflow.com/questions/13903611/reverse-the-direction-of-a-linearlayout
//        for(int k = survey.getChildCount()-1 ; k >= 0 ; k--)
//        {
//            View item = survey.getChildAt(k);
//            survey.removeViewAt(k);
//            survey.addView(item);
//        }
    }
    //public Question getter, or method(s) to load/save in this class.
    public void saveEverything(String key, SharedPreferences sharedPrefs) {
        _savedData = new HashMap<>();
        for(int i = 0; i < _questions.size(); i++) {
            Field question = _questions.get(i);
            String data = question.saveViewData();
            _savedData.put(question._name, data);
        }

        _json.writeToSharedPref(_savedData, key, sharedPrefs);
    }
    public void loadEverything(String key, SharedPreferences sharedPref) {
        _savedData = _json.readFromSharedPref(key, sharedPref);
    }
    private Field parseEntryIntoObject(String entry) {
        /******************************************************
         Read string (single line from config file)
         Split on commas with zero or more whitespace, and equal signs with any surrounding whitespace.
         ['\s' means whitespace using an additional escape character -> '\\s' to work
         '*' means zero or more times -> '\\s*' any amount of whitespace
         https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
         Update: Unable to get RegularExpressions working in Splitter class. Relying on exporting
         csv with no spaces from Excel. Excel will also hold template file to export csv.
         ToDo: Develop Excel Template file with drop down list for type, use file to export into csv.
         *****************************************************/
        Map<String,String> map = Splitter.on(",").trimResults().withKeyValueSeparator(":").split(entry);

        //Create java object representation
        SurveyQuestionsType q = SurveyQuestionsType.valueOf(map.get("type"));
        switch (q) {
            //Can this be moved into enum constructors?
            case TEXT_BOX:
                return new Survey_Text_Box(map);
            case COUNTER:
                return new Survey_Counter(map);
            case SLIDER:
                return new Survey_Slider(map);
            case CHECKBOXES:
                return new Survey_Checkboxes(map);
            case SWITCHES:
                return new Survey_Switches(map);
            case RADIO:
                return new Survey_Radio(map);
            case CHIPS:
                return new Survey_Chips(map);
            default:
                Log.e("parseEntryIntoObject", "Unable to create object");
        }

        //return abstract object

        return null;
    }
}
