package team.cake.theredalliance;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.google.android.material.slider.Slider;

import java.util.Map;
import java.util.Random;

public class Survey_Slider extends Field implements Askable{
    private final String TAG = "Survey_Slider";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public Survey_Slider(Map<String, String> map) {
        super(map);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void makeView(ViewGroup parent) {
        LinearLayout view = generateContainer();
        SeekBar seekBar = new SeekBar(_activity.get());
        seekBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 30));
        seekBar.setMin(0);
        seekBar.setMax(100);
        view.addView(seekBar);

        parent.addView(view);
    }

    @Override
    public String saveViewData(){
        StringBuilder result = new StringBuilder(getName());
        LinearLayout layout = _activity.get().findViewById(_id);
        View view = layout.getChildAt(1);

        if(view instanceof SeekBar) {
            SeekBar item = (SeekBar)view;
            String text = String.valueOf(item.getProgress());
            result.append(",value,").append(text);
        }
        if (result.toString() == getName()) {
            result.append(",null");
            Log.d(TAG, "EditText Not Found in " + getName());
        }
        Log.d("Saving_SeekBar", result.toString());
        return result.toString();
    }
}
