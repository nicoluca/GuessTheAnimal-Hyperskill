package animals.domain.animals;

import animals.util.StringUtil;

public class Animal implements QuestionInterface {
    private final String name;

    public Animal(String input) {
        this.name = StringUtil.formatAnimalInput(input);
    }

    public String getQuestion() {
        return "Is it " + name + "?";
    }

   @Override
    public String toString() {
        return name;
    }

    public String getFactQuestion() {
        return "Is the statement correct for " + name + "?";
    }

}
