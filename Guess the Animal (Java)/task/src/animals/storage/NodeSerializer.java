package animals.storage;

import animals.domain.animals.Animal;
import animals.domain.animals.QuestionInterface;
import animals.domain.tree.Node;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class NodeSerializer extends JsonSerializer<Node<QuestionInterface>> {
    @Override
    public Class<Node<QuestionInterface>> handledType() {
        return (Class<Node<QuestionInterface>>) (Class<?>) Node.class;
    }

    @Override
    public void serialize(Node<QuestionInterface> node, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeStartObject();
        if (node.getData() instanceof Animal) {
            jg.writeStringField("type", "animal");
            jg.writeObjectField("value", node.getData());
        } else {
            jg.writeStringField("type", "animalFact");
            jg.writeObjectField("value", node.getData());
        }

        if (!(node.getYes() == null) || !(node.getNo() == null)) {
            jg.writeObjectField("yes", node.getYes());
            jg.writeObjectField("no", node.getNo());
        }

        jg.writeEndObject();
    }
}
