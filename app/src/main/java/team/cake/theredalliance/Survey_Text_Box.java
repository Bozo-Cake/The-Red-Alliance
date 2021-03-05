package team.cake.theredalliance;

import java.util.List;

public class Survey_Text_Box extends Field implements Askable {
    Survey_Text_Box(String name, SurveyQuestionsType type, Object data, List extras) {

        super(name, type, data, extras);
    }

    @Override
    public Field makeView() {
        return null;
    }
}
