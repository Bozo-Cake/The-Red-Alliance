package team.cake.theredalliance;

import android.widget.LinearLayout;

import java.util.Map;

public class Survey_Checkboxes extends Field{
    public Survey_Checkboxes(Map<String, String> map) {
        /*
        List desired parameters to be required or optional to be included in config.csv here:
        Excludes parameters handled by parent class [Field]: name, type
        -List<String> selections    (required)
        -Number of checkboxes       (required)
        -default selections?        (optional)
        */

        //ToDo: Define .csv List<String> of values formatting. Extract here.

        //pass on remaining items to parent class.
        super(map);
    }

}
