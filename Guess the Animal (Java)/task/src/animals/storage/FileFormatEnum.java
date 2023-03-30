package animals.storage;

import animals.util.LocalizationUtil;

public enum FileFormatEnum {
    XML,
    YAML,
    JSON;

    public String getFileName() {
        return getLocalizedPrefix() + "." + this.toString().toLowerCase();
    }

    // specify the file extension depending on localization
    private String getLocalizedPrefix() {
        if (LocalizationUtil.getLanguage().equals("en"))
            return "animals";
        else
            return "animals_" + LocalizationUtil.getLanguage();
    }
}