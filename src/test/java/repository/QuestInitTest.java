package repository;

import entity.Answer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestInitTest {

    private QuestInit questInit;

    @BeforeEach
    void setup() {
        questInit = new QuestInit();
    }

    @Test
    void questionAnswersInit() {
        assertEquals(7, questInit.getQuestions().size());
        assertEquals(6, questInit.getAnswers().size());
    }

    @Test
    void getAnswersById_IdLessZero() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            questInit.getAnswersById(-1);
        });

        assertEquals("Id cannot be less than zero.", exception.getMessage());

    }

    @Test
    void getAnswersById() {

        Answer answer = Answer.builder()
                .text("Принять вызов")
                .nextQuestionId(2)
                .prevQuestionId(1)
                .build();

        Answer answer1 = Answer.builder()
                .text("Отклонить вызов")
                .nextQuestionId(3)
                .prevQuestionId(1)
                .build();

        List<Answer> answers = List.of(answer, answer1);

        assertIterableEquals(answers, questInit.getAnswersById(1));

    }

}