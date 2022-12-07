package repository;

import entity.Answer;
import entity.Question;
import entity.QuestionTypeEnum;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class QuestRepository {

    private Map<Long, Question> questions = new HashMap<>();
    private Map<Long, List<Answer>> answersByQuestion = new HashMap<>();
    private static final Logger logger = LogManager.getLogger();
    public static final String ID_LESS_ZERO = "Id cannot be less than zero.";
    public static final String NULL_LIST_ANSWERS = "List of answers cannot be null.";
    public static final String NULL_QUESTION = "Question cannot be null.";

    public QuestRepository(Map<Long, Question> questions, Map<Long, List<Answer>> answersByQuestion) {
        this.questions = questions;
        this.answersByQuestion = answersByQuestion;
    }

    public void saveAnswers(long id, List<Answer> answers) {

        validateId(id);

        if (answers == null) {
            logger.error(NULL_LIST_ANSWERS);
            throw new IllegalArgumentException(NULL_LIST_ANSWERS);
        }

        answersByQuestion.put(id, answers);
    }

    public void saveQuestion(long id, Question question) {

        if (id < 0) {
            logger.error(ID_LESS_ZERO);
            throw new IllegalArgumentException(ID_LESS_ZERO);
        }

        if (question == null) {
            logger.error(NULL_QUESTION);
            throw new IllegalArgumentException(NULL_QUESTION);
        }

        questions.put(id, question);
    }

    public String getQuestionText(long id) {

        validateId(id);
        return questions.get(id).getText();

    }

    public List<Answer> getAnswersById(long id) {

        validateId(id);
        return answersByQuestion.get(id);

    }

    public QuestionTypeEnum getQuestionType(long id) {

        validateId(id);
        return questions.get(id).getType();

    }

    private void validateId(long id) {

        if (id < 0) {
            logger.error(ID_LESS_ZERO);
            throw new IllegalArgumentException(ID_LESS_ZERO);
        }

        if (!questions.containsKey(id)) {
            logger.error("No matching question was found for the passed id parameter [{}].", id);
            throw new IllegalArgumentException("Incorrect value of passed parameter Id.");
        }

    }
}
