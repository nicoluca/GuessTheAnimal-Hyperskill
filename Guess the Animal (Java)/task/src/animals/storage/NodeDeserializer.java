package animals.storage;

import animals.domain.animals.Animal;
import animals.domain.animals.AnimalFact;
import animals.domain.animals.QuestionInterface;
import animals.domain.tree.Node;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class NodeDeserializer extends JsonDeserializer<Node<QuestionInterface>> {
    @Override
    public Node<QuestionInterface> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);

        String type = node.get("type").asText();
        JsonNode value = node.get("value");

        QuestionInterface question;
        if (type.equals("animal"))
            question = codec.treeToValue(value, Animal.class);
        else
            question = codec.treeToValue(value, AnimalFact.class);

        if (node.get("yes") == null || node.get("no") == null)
            return new Node<>(question);

        Node<QuestionInterface> yes = codec.treeToValue(node.get("yes"), Node.class);
        Node<QuestionInterface> no = codec.treeToValue(node.get("no"), Node.class);
        Node<QuestionInterface> returnNode = new Node<>(question);

        returnNode.setYes(yes);
        returnNode.setNo(no);

        return returnNode;
    }
}