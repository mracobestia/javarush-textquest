package service;

import entity.QuestionInfo;
import entity.QuestionTypeEnum;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.QuestRepository;

public class QuestionService {

    private final QuestRepository questRepository;

    private static final Logger logger = LogManager.getLogger();

    public QuestionService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    public QuestionInfo getQuestionInfo(User user) {

        if (user == null) {
            logger.error("User is null");
            throw new IllegalArgumentException("User cannot be null.");
        }

        QuestionTypeEnum type = questRepository.getQuestionType(user.getCurrentQuestionId());

        return QuestionInfo.builder()
                .questionText(questRepository.getQuestionText(user.getCurrentQuestionId()))
                .isGameEnd(type != QuestionTypeEnum.CONTINUE)
                .answers(questRepository.getAnswersById(user.getCurrentQuestionId()))
                .build();

    }

    public void setNextQuestionForUser(String answer, User user) {

        if (user == null) {
            logger.error("User is null");
            throw new IllegalArgumentException("User cannot be null.");
        }

        try {
            long nextQuestionId = Long.parseLong(answer);
            user.setCurrentQuestionId(nextQuestionId);
        } catch (NumberFormatException ex) {
            logger.error("It was not possible to get Id of the following question by value [{}]", answer);
            throw new NumberFormatException("Question id is not a number.");
        }

    }

}
