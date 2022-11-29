package service;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StartGameServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    User user;

    private StartGameService service;

    @BeforeEach
    void setup() {
        service = new StartGameService(userRepository);
    }

    @Test
    void setNewGameUserData_NullUser() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.setNewGameUserData(null);
        });

        assertEquals("User cannot be null.", exception.getMessage());

    }

    @Test
    void setNewGameUserData() {

        Mockito.when(user.getGamesCount()).thenReturn(0);

        service.setNewGameUserData(user);

        verify(user, times(1)).setGamesCount(1);
        verify(user, times(1)).setCurrentQuestionId(1);

    }

    @Test
    void getCreateUser_GetExistUser() {

        String name = "userName";

        Mockito.when(userRepository.isUserExist(name)).thenReturn(true);
        Mockito.when(userRepository.userByName(name)).thenReturn(user);

        assertEquals(user, service.getCreateUser(name));

    }

    @Test
    void getCreateUser_CreateUser() {

        String name = "userName";

        Mockito.when(userRepository.isUserExist(name)).thenReturn(false);

        assertEquals(new User(name, 1, 1), service.getCreateUser(name));

    }
}