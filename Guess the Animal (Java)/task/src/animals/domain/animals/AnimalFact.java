package animals.domain.animals;

import animals.domain.TypeOfFact;
import animals.util.StringUtil;

public class AnimalFact implements QuestionInterface {
    private TypeOfFact typeOfFact;
    private String fact;

    private AnimalFact(TypeOfFact typeOfFact, String fact) {
        this.typeOfFact = typeOfFact;
        this.fact = fact;
    }

    public static AnimalFact generateFromString(String fact) {
        if (!StringUtil.sentenceIsFact(fact))
            throw new IllegalArgumentException("The sentence is not a fact: " + fact);

        return new AnimalFact(StringUtil.getTypeOfFact(fact), StringUtil.formatFact(fact));
    }

    @Override
    public String toString() {
        return "AnimalFact{" +
                "typeOfFact=" + typeOfFact +
                ", fact='" + fact + '\'' +
                '}';
    }

    public String getStringForTrue() {
        switch (this.typeOfFact) {
            case CAN:
                return "can " + this.fact + ".";
            case HAS:
                return "has " + this.fact + ".";
            case IS:
                return "is " + this.fact + ".";
            default:
                throw new IllegalStateException("Unexpected value: " + this.typeOfFact);
        }
    }

    public String getStringForFalse() {
        switch (this.typeOfFact) {
            case CAN:
                return "can't " + this.fact + ".";
            case HAS:
                return "doesn't have " + this.fact + ".";
            case IS:
                return "isn't " + this.fact + ".";
            default:
                throw new IllegalStateException("Unexpected value: " + this.typeOfFact);
        }
    }

    public String getQuestion() {
        switch (this.typeOfFact) {
            case CAN:
                return "Can it " + this.fact + "?";
            case HAS:
                return "Does it have " + this.fact + "?";
            case IS:
                return "Is it " + this.fact + "?";
            default:
                throw new IllegalStateException("Unexpected value: " + this.typeOfFact);
        }
    }
}
