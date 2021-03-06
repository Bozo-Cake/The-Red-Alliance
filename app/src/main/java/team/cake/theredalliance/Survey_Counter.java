package team.cake.theredalliance;

import java.util.Map;

public class Survey_Counter extends Field {
    public Survey_Counter(Map<String, String> map) {
        /*
        List desired parameters to be required or optional to be included in config.csv here:
        Excludes parameters handled by parent class [Field]: name, type
        -Int data (required)
        -Count by (optional if default to 1) (extra optional: multiple count by buttons - +1, +5, +10)
        -Count when long press - timer while holding down button to time events (optional)
        */

        //ToDo: Extract Counter data here.

        //pass on remaining items to parent class.
        super(map);
    }
}
