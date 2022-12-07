package repository;

import entity.Answer;
import entity.Question;
import entity.QuestionTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QuestRepositoryTest {

    private Map<Long, List<Answer>> answersByQuestion;
    private Map<Long, Question> questions;
    private QuestRepository repository;
    private List<Answer> answersList;

    @BeforeEach
    void setup() {
        questions = new HashMap<>();
        questions.put(1L,
                Question.builder()
                        .id(1)
                        .text("Question text")
                        .type(QuestionTypeEnum.CONTINUE)
                        .build());
        questions.put(2L,
                Question.builder()
                        .id(2)
                        .text("Question text 2")
                        .type(QuestionTypeEnum.END)
                        .build());

        answersList = List.of(Answer.builder()
                .text("Answer test text")
                .nextQuestionId(2)
                .prevQuestionId(1)
                .build());

        answersByQuestion = new HashMap<>();
        answersByQuestion.put(1L, answersList);

        repository = new QuestRepository(questions, answersByQuestion);

    }

    @Test
    void saveAnswers_IdLessZero() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.saveAnswers(-1, answersList);
        });

        assertEquals("Id cannot be less than zero.", exception.getMessage());

    }

    @Test
    void saveAnswers_NoQuestionWithId() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.saveAnswers(10, answersList);
        });

        assertEquals("Incorrect value of passed parameter Id.", exception.getMessage());

    }

    @Test
    void saveAnswers_NullAnswersList() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.saveAnswers(1, null);
        });

        assertEquals("List of answers cannot be null.", exception.getMessage());

    }

    @Test
    void saveAnswers() {

        repository.saveAnswers(1, answersList);

        assertEquals(1, answersByQuestion.size());

    }

    @Test
    void saveQuestion_IdLessZero() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.saveQuestion(-1,
                    Question.builder()
                    .id(-1)
                    .text("Question text 3")
                    .type(QuestionTypeEnum.CONTINUE)
                    .build());
        });

        assertEquals("Id cannot be less than zero.", exception.getMessage());

    }

    @Test
    void saveQuestion_NullQuestion() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.saveQuestion(1, null);
        });

        assertEquals("Question cannot be null.", exception.getMessage());

    }

    @Test
    void saveQuestion() {

        repository.saveQuestion(3,
                Question.builder()
                        .id(3)
                        .text("Question text 3")
                        .type(QuestionTypeEnum.CONTINUE)
                        .build());

        assertEquals(3, questions.size());

    }

    @Test
    void getQuestionText_IdLessZero() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.getQuestionText(-1);
        });

        assertEquals("Id cannot be less than zero.", exception.getMessage());

    }

    @Test
    void getQuestionText_NoQuestionInList() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.getQuestionText(10);
        });

        assertEquals("Incorrect value of passed parameter Id.", exception.getMessage());
    }

    @Test
    void getQuestionText() {

        assertEquals("Question text", repository.getQuestionText(1));

    }

    @Test
    void getAnswersById() {
        assertEquals(answersList, repository.getAnswersById(1));
    }

    @Test
    void getQuestionType() {
        assertEquals(QuestionTypeEnum.CONTINUE, repository.getQuestionType(1));
    }
}