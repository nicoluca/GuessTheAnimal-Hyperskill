package animals.util;

import animals.Main;
import animals.storage.FileFormatEnum;

public class ArgsUtil {
    public static FileFormatEnum getFileFormat(String[] args) {
        FileFormatEnum fileFormatEnum = FileFormatEnum.JSON;
        if (args.length == 0) {
            Main.LOGGER.info("No arguments specified. Using JSON.");
            return fileFormatEnum;
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-type")) {
                try {
                    fileFormatEnum = FileFormatEnum.valueOf(args[i + 1].toUpperCase());
                    Main.LOGGER.info("Using " + args[i + 1].toUpperCase() + " format.");
                } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                    Main.LOGGER.warning("No valid file format specified. Using JSON.");
                }
            }
        }

        return fileFormatEnum;
    }
}
