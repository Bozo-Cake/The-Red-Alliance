package team.cake.theredalliance;

import java.util.Map;

public class Survey_Slider extends Field {
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
}
