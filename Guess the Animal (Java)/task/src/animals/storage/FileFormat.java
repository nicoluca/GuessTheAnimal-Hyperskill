package animals.storage;

public enum FileFormat {
    XML,
    YAML,
    JSON;

    public String getFileName() {
        return "animals." + this.toString().toLowerCase();
    }
}