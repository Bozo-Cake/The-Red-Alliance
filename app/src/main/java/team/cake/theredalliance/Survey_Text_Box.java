package team.cake.theredalliance;

import android.widget.LinearLayout;

import java.util.Map;

public class Survey_Text_Box extends Field implements Askable {
    public Survey_Text_Box(Map<String,String> map) {
        /*
        List desired parameters to be required or optional to be included in config.csv here:
        Excludes parameters handled by parent class [Field]: name, type
        -Max Length (optional)
        */
        //ToDo: Extract TextBox data here.

        //pass on remaining items to parent class.
        super(map);
    }

    @Override
    public LinearLayout makeView() {
        return null;
    }
}
