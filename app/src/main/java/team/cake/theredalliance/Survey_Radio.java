package team.cake.theredalliance;

import java.util.Map;

public class Survey_Radio extends Field {
    public Survey_Radio(Map<String, String> map) {
        /*
        List desired parameters to be required or optional to be included in config.csv here:
        Excludes parameters handled by parent class [Field]: name, type
        -Number of Radios       (required)
        -List<String> values    (required)
        -List<bool> defaults    (optional)
        */
        //ToDo: Extract Radio data here.

        //pass on remaining items to parent class.
        super(map);
    }
}
