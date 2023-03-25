package animals.domain.animals;

import animals.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class Animal implements QuestionInterface {
    private final String name;

    public Animal(String input) {
        this.name = StringUtil.formatAnimalInput(input);
    }

    @JsonInclude
    public String getName() {
        return name;
    }

    @JsonIgnore
    public String getQuestion() {
        return "Is it " + name + "?";
    }

    @Override
    public String toString() {
        return name;
    }

    @JsonIgnore
    public String getFactQuestion() {
        return "Is the statement correct for " + name + "?";
    }
}
