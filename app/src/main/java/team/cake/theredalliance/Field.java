package team.cake.theredalliance;

import java.util.List;

public abstract class Field<T> {
    String _name;
    SurveyQuestionsType _type;
    T _data;
    List<String> _extras;

    Field(String name, SurveyQuestionsType type, T data, List<String> extras) {
        _name = name;
        _type = type;
        _data = data;
        _extras = extras;
    }

    String getName() { return _name; }
    SurveyQuestionsType getType() { return _type; }
    T getData() { return _data; }

}
