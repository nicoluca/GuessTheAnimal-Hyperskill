package animals.domain.animals;

import animals.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class AnimalFact implements QuestionInterface {
    private final TypeOfFact typeOfFact;
    private final String fact;

    // No-arg constructor for Jackson
    public AnimalFact() {
        this.typeOfFact = TypeOfFact.IS;
        this.fact = "";
    }

    private AnimalFact(TypeOfFact typeOfFact, String fact) {
        this.typeOfFact = typeOfFact;
        this.fact = fact;
    }

    public static AnimalFact generateFromString(String fact) {
        if (!StringUtil.sentenceIsFact(fact))
            throw new IllegalArgumentException("The sentence is not a fact: " + fact);

        return new AnimalFact(StringUtil.getTypeOfFact(fact), StringUtil.formatFact(fact));
    }

    @JsonInclude
    public TypeOfFact getTypeOfFact() {
        return typeOfFact;
    }

    @JsonInclude
    public String getFact() {
        return fact;
    }

    @Override
    public String toString() {
        return getQuestion();
    }

    @JsonIgnore
    public String getStringForTrue() {
        return switch (this.typeOfFact) {
            case CAN -> "can " + this.fact + ".";
            case HAS -> "has " + this.fact + ".";
            case IS -> "is " + this.fact + ".";
        };
    }

    @JsonIgnore
    public String getStringForFalse() {
        return switch (this.typeOfFact) {
            case CAN -> "can't " + this.fact + ".";
            case HAS -> "doesn't have " + this.fact + ".";
            case IS -> "isn't " + this.fact + ".";
        };
    }

    @JsonIgnore
    public String getQuestion() {
        return switch (this.typeOfFact) {
            case CAN -> "Can it " + this.fact + "?";
            case HAS -> "Does it have " + this.fact + "?";
            case IS -> "Is it " + this.fact + "?";
        };
    }
}
