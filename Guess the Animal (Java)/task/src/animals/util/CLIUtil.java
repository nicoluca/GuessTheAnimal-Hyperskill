package animals.util;

import animals.Main;

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
            System.out.println(LocalizationUtil.getRandomMessage("constants.clarification"));
            answer = Main.scanner.nextLine();
        }
        return StringUtil.isPositiveAnswer(answer);
    }

    public static int getInt() {
        try {
            return Integer.parseInt(Main.scanner.nextLine());
        } catch (NumberFormatException e) {
            Main.LOGGER.warning("Invalid input");
            System.out.println(LocalizationUtil.getMessage("cliutil.invalidnumber"));
            return getInt();
        }
    }
}
