package team.cake.theredalliance;

import android.view.View;
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
        counter.setText("0");
        view.addView(counter);

        //Buttons to the right to increase, decrease
        Button down = new Button(_activity.get());
        down.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        down.setText("-");
        down.setTextSize(20f);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter.setText(String.valueOf(Integer.parseInt(counter.getText().toString()) - 1));
            }
        });
        view.addView(down);

        Button up = new Button(_activity.get());
        up.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        up.setText("+");
        up.setTextSize(20f);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter.setText(String.valueOf(Integer.parseInt(counter.getText().toString()) + 1));
            }
        });
        view.addView(up);

        parent.addView(view);
    }

    @Override
    public String saveViewData() {
        return null;
    }
}
