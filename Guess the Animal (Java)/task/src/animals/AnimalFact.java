package animals;

public class AnimalFact {
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
                return "doesn't have " + this.fact; // Without period, messy requirement.
            case IS:
                return "isn't " + this.fact; // Without period, messy requirement.
            default:
                throw new IllegalStateException("Unexpected value: " + this.typeOfFact);
        }
    }
}
