package animals.storage;

import animals.Main;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class FileManager {
    private final FileFormat fileFormat;
    private final ObjectMapper objectMapper;

    public FileManager(FileFormat fileFormat) {
        this.fileFormat = fileFormat;

        switch (fileFormat) {
            case XML:
                this.objectMapper = new XmlMapper();
                break;
            case YAML:
                this.objectMapper = new YAMLMapper();
                break;
            case JSON:
                this.objectMapper = new JsonMapper();
                break;
            default:
                Main.LOGGER.warning("Unknown file format: " + fileFormat + ". Using JSON.");
                this.objectMapper = new JsonMapper();
        }
    }

    public void save(Object object) {
        if (Paths.get(this.fileFormat.getFileName()).toFile().exists()) {
            Main.LOGGER.info("File " + this.fileFormat.getFileName() + " already exists. Overwriting.");
            // Paths.get(this.fileFormat.getFileName()).toFile().delete();
        }

        try {
            this.objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(new File(this.fileFormat.getFileName()), object);
            Main.LOGGER.info("Saved " + object.toString() + " to " + this.fileFormat.getFileName());
        } catch (IOException e) {
            Main.LOGGER.warning("Could not save " + object.toString() + " to " + this.fileFormat.getFileName());
            e.printStackTrace();
        }
    }
}
