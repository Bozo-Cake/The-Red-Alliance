package team.cake.theredalliance;

public abstract class Field<T> {
    String _name;
    SurveyQuestions _type;
    T _data;

    Field(String name, SurveyQuestions type, T data) {
        _name = name;
        _type = type;
        _data = data;
    }

    String getName() { return _name; }
    SurveyQuestions getType() { return _type; }
    T getData() { return _data; }

}
