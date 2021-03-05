package team.cake.theredalliance;

import java.util.List;
import java.util.Map;

public abstract class Field {
    String _name;
    SurveyQuestionsType _type;
    Map<String,String> _extras;

    Field(Map<String,String> map) {
        _name = map.get("name");
        map.remove("name");
        _type = SurveyQuestionsType.valueOf(map.get("type"));
        map.remove("type");
        _extras = map;
        //ToDo: throw exception in any Field object constructor for ConfigReader to disregard faulty file
    }

    String getName() { return _name; }
    SurveyQuestionsType getType() { return _type; }
    Map<String,String> getExtras() { return _extras; }
}
