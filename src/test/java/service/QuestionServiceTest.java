package service;

import entity.QuestionInfo;
import entity.QuestionTypeEnum;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.QuestRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    QuestRepository questRepository;

    @Mock
    User user;

    private QuestionService service;

    @BeforeEach
    void setup() {
        service = new QuestionService(questRepository);
    }

    @Test
    void getQuestionInfo_NullUser() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.getQuestionInfo(null);
        });

        assertEquals("User cannot be null.", exception.getMessage());

    }

    @Test
    void getQuestionInfo() {

        Mockito.when(questRepository.getQuestionText(1)).thenReturn("Question text");
        Mockito.when(questRepository.getQuestionType(1)).thenReturn(QuestionTypeEnum.CONTINUE);
        Mockito.when(questRepository.getAnswersById(1)).thenReturn(new ArrayList<>());

        Mockito.when(user.getCurrentQuestionId()).thenReturn(1L);

        QuestionInfo info = QuestionInfo.builder()
                .questionText("Question text")
                .isGameEnd(false)
                .answers(new ArrayList<>())
                .build();

        assertEquals(info, service.getQuestionInfo(user));
    }

    @Test
    void setNextQuestionForUser_NullUser() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.setNextQuestionForUser("2",null);
        });

        assertEquals("User cannot be null.", exception.getMessage());

    }

    @Test
    void setNextQuestionForUser_NotNumberAnswer() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.setNextQuestionForUser("answer", user);
        });

        assertEquals("Question id is not a number.", exception.getMessage());

    }

    @Test
    void setNextQuestionForUser() {

        service.setNextQuestionForUser("2", user);
        verify(user, times(1)).setCurrentQuestionId(2);

    }
}