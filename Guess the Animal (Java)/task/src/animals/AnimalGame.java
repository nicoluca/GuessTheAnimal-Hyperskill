package animals;

public class AnimalGame {
    private String animal1;
    private String animal2;
    private String distinguishingFact;
    private boolean trueForAnimal1;

    public AnimalGame() {
    }

    public void start() {
        String animal1 = CLIUtil.getString("Enter the first animal:");
        this.animal1 = StringUtil.formatAnimalInput(animal1);
        String animal2 = CLIUtil.getString("Enter the second animal:");
        this.animal2 = StringUtil.formatAnimalInput(animal2);
        this.distinguishingFact = CLIUtil.getString("Specify a question that distinguishes " +
                this.animal1 + " from " + this.animal2 + ".");
        this.trueForAnimal1 = CLIUtil.isYesAnswer("Is it correct for " +
                this.animal2 + "?");
    }
}
