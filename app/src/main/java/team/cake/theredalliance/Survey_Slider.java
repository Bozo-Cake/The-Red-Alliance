package team.cake.theredalliance;

import android.os.Build;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.google.android.material.slider.Slider;

import java.util.Map;
import java.util.Random;

public class Survey_Slider extends Field implements Askable{
    String _data;
    int _id;
    public Survey_Slider(Map<String, String> map) {
        /*
        List desired parameters to be required or optional to be included in config.csv here:
        Excludes parameters handled by parent class [Field]: name, type
        -Range Beginning - autodetect type  (required)
        -Range End       - autodetect type  (required)
        -Default Value                      (optional)
        */
        //ToDo: Extract Slider data here.

        //pass on remaining items to parent class.
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

    public String saveViewData(){
        EditText textView = _activity.get().findViewById(_id);
        return textView.getText().toString();
    }
}
