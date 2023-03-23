package animals;

public class CLIUtil {

    public static String getString() {
        return Main.scanner.nextLine();
    }

    public static String getString(String prompt) {
        System.out.println(prompt);
        return Main.scanner.nextLine();
    }

    public static boolean isYesAnswer(String prompt) {
        System.out.println(prompt);
        String answer = Main.scanner.nextLine();
        while (!StringUtil.isPositiveAnswer(answer) && !StringUtil.isNegativeAnswer(answer)) {
            System.out.println(StringUtil.getClarification());
            answer = Main.scanner.nextLine();
        }
        return StringUtil.isPositiveAnswer(answer);
    }
}
