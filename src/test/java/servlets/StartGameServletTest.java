package servlets;

import entity.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.UserRepository;
import service.StartGameService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StartGameServletTest {

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
    StartGameService startGameService;

    @Mock
    User user;

    StartGameServlet servlet;

    @BeforeEach
    void setup() throws ServletException {
        Mockito.when(servletConfig.getServletContext()).thenReturn(context);
        Mockito.when(context.getAttribute(eq("startGameService"))).thenReturn(startGameService);
        Mockito.when(request.getSession()).thenReturn(session);

        servlet = new StartGameServlet();
        servlet.init(servletConfig);
    }

    @Test
    void doPost_When_UserIsInSession() throws ServletException, IOException {

        Mockito.when(session.getAttribute(eq("user"))).thenReturn(user);

        servlet.doPost(request, response);

        verify(startGameService, times(1)).setNewGameUserData(user);
        verify(response, times(1)).sendRedirect(eq("question"));

    }

    @Test
    void doPost_When_UserIsNotInSession() throws ServletException, IOException {

        String name = "userName";

        Mockito.when(request.getParameter(eq("name"))).thenReturn(name);
        Mockito.when(session.getAttribute(eq("user"))).thenReturn(null);
        Mockito.when(startGameService.getCreateUser(name)).thenReturn(user);

        servlet.doPost(request, response);

        verify(startGameService, times(1)).getCreateUser(name);
        verify(session, times(1)).setAttribute("user", user);
        verify(response, times(1)).sendRedirect(eq("question"));

    }


}