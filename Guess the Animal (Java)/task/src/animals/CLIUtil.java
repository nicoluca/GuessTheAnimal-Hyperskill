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
        return StringUtil.isPositiveAnswer(Main.scanner.nextLine());
    }
}
