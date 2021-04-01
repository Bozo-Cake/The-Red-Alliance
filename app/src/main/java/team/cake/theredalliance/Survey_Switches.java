package team.cake.theredalliance;

import android.os.Build;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;


import androidx.annotation.RequiresApi;

import java.util.Map;

public class Survey_Switches extends Field{
    private final String TAG = "Survey_Switches";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public Survey_Switches(Map<String, String> map) {
        super(map);
    }
    public void makeView(ViewGroup parent) {
        LinearLayout view = generateContainer();
        view.setId(_id);
        for (int i = 0; _map.containsKey(String.valueOf(i)); i++) {
            Switch switchy = new Switch(_activity.get());
            switchy.setTag(_map.get(String.valueOf(i)));
            switchy.setText(_map.get(String.valueOf(i)));
            switchy.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            view.addView(switchy);
        }
        parent.addView(view);
    }
    @Override
    public String saveViewData(){
        EditText textView = _activity.get().findViewById(_id);
        return textView.getText().toString();
    }
}
