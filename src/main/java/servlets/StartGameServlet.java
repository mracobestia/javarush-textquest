package servlets;

import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.StartGameService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "StartGameServlet", value = "/startgame")
public class StartGameServlet extends HttpServlet {
    private StartGameService startGameService;
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext context = config.getServletContext();
        startGameService = (StartGameService) context.getAttribute("startGameService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");

        HttpSession session = req.getSession();
        User user;

        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
            startGameService.setNewGameUserData(user);

            logger.debug("Пользователь [{}] начал игру повторно", user.getName());

            resp.sendRedirect("question");
            return;
        }

        user = startGameService.getCreateUser(name);
        session.setAttribute("user", user);
        resp.sendRedirect("question");

    }
}
