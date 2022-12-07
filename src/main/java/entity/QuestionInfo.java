package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class QuestionInfo {

    String questionText;
    boolean isGameEnd;
    List<Answer> answers;

}
