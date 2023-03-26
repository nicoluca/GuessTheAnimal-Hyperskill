package animals.domain.animals;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public interface QuestionInterface {
    String getQuestion();
}
