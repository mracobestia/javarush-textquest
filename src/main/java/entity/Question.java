package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Question {

    private long id;
    private String text;
    private QuestionTypeEnum type;

}
