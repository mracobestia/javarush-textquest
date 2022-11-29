package servlets;

import entity.Answer;
import entity.QuestionInfo;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import service.QuestionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QuestionServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    ServletConfig servletConfig;

    @Mock
    ServletContext context;

    @Mock
    QuestionService questionService;

    @Mock
    QuestionInfo info;

    @Mock
    User user;

    @Mock
    RequestDispatcher requestDispatcher;

    QuestionServlet servlet;

    @BeforeEach
    void setup() throws ServletException {
        Mockito.when(servletConfig.getServletContext()).thenReturn(context);
        Mockito.when(context.getAttribute(eq("questionService"))).thenReturn(questionService);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(eq("user"))).thenReturn(user);

        servlet = new QuestionServlet();
        servlet.init(servletConfig);
    }

    @Test
    void doGet() throws ServletException, IOException {

        List<Answer> answers = new ArrayList<>();

        Mockito.when(questionService.getQuestionInfo(user)).thenReturn(info);
        Mockito.when(info.getQuestionText()).thenReturn("Text");
        Mockito.when(info.isGameEnd()).thenReturn(true);
        Mockito.when(info.getAnswers()).thenReturn(answers);
        Mockito.when(context.getRequestDispatcher(eq("/WEB-INF/question.jsp")))
                .thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(request, times(1)).setAttribute("questionText", "Text");
        verify(request, times(1)).setAttribute("isGameEnd", true);
        verify(request, times(1)).setAttribute("answers", answers);
        verify(request, times(1)).setAttribute("user", user);
        verify(requestDispatcher, times(1))
                .forward(request, response);

    }

    @Test
    void doPost() throws IOException, ServletException {

        Mockito.when(request.getParameter(eq("radioOptions"))).thenReturn("1");

        servlet.doPost(request, response);

        verify(questionService, times(1)).setNextQuestionForUser("1", user);
        verify(response, times(1)).sendRedirect(eq("question"));

    }
}