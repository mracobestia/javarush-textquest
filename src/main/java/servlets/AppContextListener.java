package servlets;

import entity.Question;
import repository.QuestInit;
import repository.QuestRepository;
import repository.UserRepository;
import service.QuestionService;
import service.StartGameService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        QuestInit questInit = new QuestInit();
        QuestRepository questRepository = new QuestRepository();
        for (Question question: questInit.getQuestions()) {
            questRepository.saveQuestion(question.getId(), question);
            questRepository.saveAnswers(question.getId(), questInit.getAnswersById(question.getId()));
        }

        context.setAttribute("questionService", new QuestionService(questRepository));
        context.setAttribute("startGameService", new StartGameService(new UserRepository()));
    }

}
