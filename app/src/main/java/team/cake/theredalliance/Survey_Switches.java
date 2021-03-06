package team.cake.theredalliance;

import java.util.Map;

public class Survey_Switches extends Field {
    public Survey_Switches(Map<String, String> map) {
        /*
        List desired parameters to be required or optional to be included in config.csv here:
        Excludes parameters handled by parent class [Field]: name, type
        -Number of Switches     (required)
        -List<String> values    (required)
        -List<bool> defaults    (optional)
        */
        //ToDo: Extract Switch data here.

        //pass on remaining items to parent class.
        super(map);
    }
}
