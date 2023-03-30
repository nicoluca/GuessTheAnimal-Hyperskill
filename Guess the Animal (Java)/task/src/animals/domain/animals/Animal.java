package animals.domain.animals;

import animals.util.LocalizationUtil;
import animals.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class Animal implements QuestionInterface {
    private final String name;

    // No-arg constructor for Jackson
    public Animal() {
        this.name = "";
    }

    public Animal(String input) {
        this.name = StringUtil.formatAnimalInput(input);
    }

    @JsonInclude
    public String getName() {
        return name;
    }

    @JsonIgnore
    public String getQuestion() {
        return LocalizationUtil.getMessageWithArgument("animal.question", name);
    }

    @Override
    public String toString() {
        return name;
    }

    @JsonIgnore
    public String getFactQuestion() {
        return LocalizationUtil.getMessageWithArgument("animal.fact.question", name);
    }

}
