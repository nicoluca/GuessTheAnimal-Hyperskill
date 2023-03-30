package animals.domain.animals;

import animals.util.LocalizationUtil;
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
            case CAN -> LocalizationUtil.getMessageWithArgument("animalfact.can", this.fact);
            case HAS -> LocalizationUtil.getMessageWithArgument("animalfact.has", this.fact);
            case IS -> LocalizationUtil.getMessageWithArgument("animalfact.is", this.fact);
        };
    }

    @JsonIgnore
    public String getStringForFalse() {
        return switch (this.typeOfFact) {
            case CAN -> LocalizationUtil.getMessageWithArgument("animalfact.cant", this.fact);
            case HAS -> LocalizationUtil.getMessageWithArgument("animalfact.hasnt", this.fact);
            case IS -> LocalizationUtil.getMessageWithArgument("animalfact.isnt", this.fact);
        };
    }

    @JsonIgnore
    public String getQuestion() {
        return switch (this.typeOfFact) {
            case CAN -> LocalizationUtil.getMessageWithArgument("animalfact.question.can", this.fact);
            case HAS -> LocalizationUtil.getMessageWithArgument("animalfact.question.has", this.fact);
            case IS -> LocalizationUtil.getMessageWithArgument("animalfact.question.is", this.fact);
        };
    }
}
