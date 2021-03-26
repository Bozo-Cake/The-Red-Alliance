package team.cake.theredalliance;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Map;
import java.util.Random;

public class Survey_Counter extends Field {

    public Survey_Counter(Map<String, String> map) {
        super(map);
    }
    @Override
    public void makeView(ViewGroup parent) {
        LinearLayout view = generateContainer();
        //overide height to specific size
        //view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150));
        //Number Text Box
        EditText counter = new EditText(_activity.get());
        counter.setInputType(2);//2 = TYPE_CLASS_NUMBER
        counter.setLayoutParams(new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.MATCH_PARENT));
        counter.setTextSize(25f);//Don't know if this works...
        view.addView(counter);

        //Buttons to the right to increase, decrease
        Button up = new Button(_activity.get());
        up.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        up.setText("+");//ToDo: Doesn't work, but does on the down button!!
        up.setTextSize(20f);
        view.addView(up);

        Button down = new Button(_activity.get());
        up.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        up.setText("-");
        up.setTextSize(20f);
        view.addView(down);

        parent.addView(view);
    }
}
