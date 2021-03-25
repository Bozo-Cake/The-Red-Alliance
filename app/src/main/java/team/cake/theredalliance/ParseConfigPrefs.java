package team.cake.theredalliance;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.common.base.Splitter;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Set;

public class ParseConfigPrefs {
    WeakReference<Activity> mee;

    ParseConfigPrefs(Activity pass) {
        mee = new WeakReference<Activity>(pass);
    }
    public Map<String, String> parse(String prefsKey) {
        SharedPreferences sharedPref = mee.get().getSharedPreferences("Config_Files", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Set<String> save = sharedPref.getStringSet(prefsKey, null);

    }
    private Field parseEntryIntoObject(String entry) {
        //ToDo: Develop Excel Template file with drop down list for type, use file to export into csv.
        Map<String, String> map = Splitter.on(",").trimResults().withKeyValueSeparator(":").split(entry);

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
            //The following three are redundant to each other
            //ToDO:(group into a single class with displayType parameter?)
            case CHECKBOXES:
                return new Survey_Checkboxes(map);
            case SWITCHES:
                return new Survey_Switches(map);
            case RADIO:
                return new Survey_Radio(map);
            default:
                Log.e("parseEntryIntoObject", "Unable to create object");
        }
        return null;
    }
}
