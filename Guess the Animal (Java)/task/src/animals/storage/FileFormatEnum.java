package animals.storage;

public enum FileFormatEnum {
    XML,
    YAML,
    JSON;

    public String getFileName() {
        return "animals." + this.toString().toLowerCase();
    }
}