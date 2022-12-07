package servlets;

import entity.QuestionInfo;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.QuestionService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "QuestionServlet", value = "/question")
public class QuestionServlet extends HttpServlet {

    private QuestionService questionService;

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext context = config.getServletContext();
        questionService = (QuestionService) context.getAttribute("questionService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        QuestionInfo info = questionService.getQuestionInfo(user);
        req.setAttribute("questionText", info.getQuestionText());
        req.setAttribute("isGameEnd", info.isGameEnd());
        req.setAttribute("answers", info.getAnswers());
        req.setAttribute("user", user);

        logger.debug("Пользователь [{}] открыл вопрос c id = [{}].", user.getName(), user.getCurrentQuestionId());

        getServletContext().getRequestDispatcher("/WEB-INF/question.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        logger.debug("Пользователь [{}] ответил на вопрос с id = [{}].", user.getName(), user.getCurrentQuestionId());

        questionService.setNextQuestionForUser(req.getParameter("radioOptions"), user);

        resp.sendRedirect("question");

    }
}
