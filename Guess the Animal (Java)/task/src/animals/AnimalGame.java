package animals;

public class AnimalGame {
    private String animal1;
    private String animal2;
    private AnimalFact distinguishingFact;
    private String animalForWhichfactApplies;

    public AnimalGame() {
    }

    public void start() {
        String animal1 = CLIUtil.getString("Enter the first animal:");
        this.animal1 = StringUtil.formatAnimalInput(animal1);
        String animal2 = CLIUtil.getString("Enter the second animal:");
        this.animal2 = StringUtil.formatAnimalInput(animal2);

        this.setDistinguishingFact();
        this.setAnimalForWhichFactApplies();
        this.printFacts();
        this.printQuestion();
    }



    private void setDistinguishingFact() {
        String prompt = "Specify a fact that distinguishes " +
                this.animal1 + " from " + this.animal2 + ".\n" +
                "The sentence should be of the format: 'It can/has/is ...'.";
        String distinguishingFact = CLIUtil.getString(prompt);

        while (!StringUtil.sentenceIsFact(distinguishingFact)) {
            System.out.println("The examples of a statement:\n" +
            "- It can fly\n" +
            "- It has horn\n" +
            "- It is a mammal");
            distinguishingFact = CLIUtil.getString(prompt);
        }

        this.distinguishingFact = AnimalFact.generateFromString(distinguishingFact);
    }



    private void setAnimalForWhichFactApplies() {
        boolean trueForAnimal2 = CLIUtil.isYesAnswer("Is it correct for " + this.animal2 + "?");

        if (trueForAnimal2)
            this.animalForWhichfactApplies = this.animal2;
        else
            this.animalForWhichfactApplies = this.animal1;
    }

    private void printFacts() {
        System.out.println("I learned the following facts about animals:");
        if (this.animalForWhichfactApplies.equals(this.animal1)) {
            System.out.println("The " + StringUtil.getWithoutArticle(this.animal1) + " " + this.distinguishingFact.getStringForTrue());
            System.out.println("The " + StringUtil.getWithoutArticle(this.animal2) + " " + this.distinguishingFact.getStringForFalse());
        } else {
            System.out.println("The " + StringUtil.getWithoutArticle(this.animal1) + " " + this.distinguishingFact.getStringForFalse());
            System.out.println("The " + StringUtil.getWithoutArticle(this.animal2) + " " + this.distinguishingFact.getStringForTrue());
        }
    }


    private void printQuestion() {
        System.out.println("I can distinguish these animals by asking the question:");
        System.out.println("- " + this.distinguishingFact.getDistinguishingQuestion());
    }
}
