package repository;

import entity.Answer;
import entity.Question;
import entity.QuestionTypeEnum;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Getter
public class QuestInit {
    private final List<Question> questions = new ArrayList<>();
    private final List<Answer> answers = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger();
    public static final String ID_LESS_ZERO = "Id cannot be less than zero.";
    public QuestInit() {
        QuestionInit();
        AnswerInit();
    }

    public void QuestionInit() {

        Question question = Question.builder()
                .id(1)
                .text("Ты потерял память. Принять вызов НЛО?")
                .type(QuestionTypeEnum.CONTINUE)
                .build();
        questions.add(question);

        question = Question.builder()
                .id(2)
                .text("Ты принял вызов. Поднимаешься на мостик к капитану?")
                .type(QuestionTypeEnum.CONTINUE)
                .build();
        questions.add(question);

        question = Question.builder()
                .id(3)
                .text("Ты отклонил вызов. Поражение")
                .type(QuestionTypeEnum.END)
                .build();
        questions.add(question);

        question = Question.builder()
                .id(4)
                .text("Ты поднялся на мостик. Ты кто?")
                .type(QuestionTypeEnum.CONTINUE)
                .build();
        questions.add(question);

        question = Question.builder()
                .id(5)
                .text("Ты не пошел на переговоры. Поражение")
                .type(QuestionTypeEnum.END)
                .build();
        questions.add(question);

        question = Question.builder()
                .id(6)
                .text("Тебя вернули домой. Победа")
                .type(QuestionTypeEnum.END)
                .build();
        questions.add(question);

        question = Question.builder()
                .id(7)
                .text("Твою ложь разоблачили. Поражение")
                .type(QuestionTypeEnum.END)
                .build();
        questions.add(question);

        logger.debug("Создание списка вопросов, всего [{}]", questions.size());

    }

    public void AnswerInit() {

        Answer answer = Answer.builder()
                .text("Принять вызов")
                .nextQuestionId(questions.get(1).getId())
                .prevQuestionId(questions.get(0).getId())
                .build();
        answers.add(answer);

        answer = Answer.builder()
                .text("Отклонить вызов")
                .nextQuestionId(questions.get(2).getId())
                .prevQuestionId(questions.get(0).getId())
                .build();
        answers.add(answer);

        answer = Answer.builder()
                .text("Подняться на мостик")
                .nextQuestionId(questions.get(3).getId())
                .prevQuestionId(questions.get(1).getId())
                .build();
        answers.add(answer);

        answer = Answer.builder()
                .text("Отказаться подниматься на мостик")
                .nextQuestionId(questions.get(4).getId())
                .prevQuestionId(questions.get(1).getId())
                .build();
        answers.add(answer);

        answer = Answer.builder()
                .text("Рассказать правду о себе")
                .nextQuestionId(questions.get(5).getId())
                .prevQuestionId(questions.get(3).getId())
                .build();
        answers.add(answer);

        answer = Answer.builder()
                .text("Солгать о себе")
                .nextQuestionId(questions.get(6).getId())
                .prevQuestionId(questions.get(3).getId())
                .build();
        answers.add(answer);

        logger.debug("Создание списка ответов, всего [{}]", answers.size());

    }

    public List<Answer> getAnswersById(long id) {

        if (id < 0) {
            logger.error(ID_LESS_ZERO);
            throw new IllegalArgumentException(ID_LESS_ZERO);
        }

        return answers.stream()
                .filter(s -> (s.getPrevQuestionId() == id))
                .toList();
    }

}
