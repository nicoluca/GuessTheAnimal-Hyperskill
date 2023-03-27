package animals.storage;

import animals.Main;
import animals.domain.animals.QuestionInterface;
import animals.domain.tree.Node;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class FileManager {
    private final FileFormatEnum fileFormatEnum;
    private final ObjectMapper objectMapper;

    public FileManager(FileFormatEnum fileFormatEnum) {
        this.fileFormatEnum = fileFormatEnum;

        switch (fileFormatEnum) {
            case XML -> this.objectMapper = new XmlMapper();
            case YAML -> this.objectMapper = new YAMLMapper();
            case JSON -> this.objectMapper = new JsonMapper();
            default -> {
                Main.LOGGER.warning("Unknown file format: " + fileFormatEnum + ". Using JSON.");
                this.objectMapper = new JsonMapper();
            }
        }

        SimpleModule module = new SimpleModule();
        module.addSerializer(new NodeSerializer());
        module.addDeserializer(Node.class, new NodeDeserializer());
        this.objectMapper.registerModule(module);
    }

    public void save(Object object) {
        if (Paths.get(this.fileFormatEnum.getFileName()).toFile().exists())
            Main.LOGGER.info("File " + this.fileFormatEnum.getFileName() + " already exists. Overwriting.");

        try {
            this.objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(new File(this.fileFormatEnum.getFileName()), object);
            Main.LOGGER.info("Saved " + object.toString() + " to " + this.fileFormatEnum.getFileName());
        } catch (IOException e) {
            Main.LOGGER.warning("Could not save " + object.toString() + " to " + this.fileFormatEnum.getFileName());
            e.printStackTrace();
        }
    }

    public boolean savedGameAvailable() {
        return Paths.get(this.fileFormatEnum.getFileName()).toFile().exists();
    }

    public Object load() throws IOException {
        return objectMapper.readValue(
                new File(this.fileFormatEnum.getFileName()),
                new TypeReference<Node<QuestionInterface>>() {});
    }
}
