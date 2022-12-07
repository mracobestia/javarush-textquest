package repository;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private List<User> users;
    private UserRepository repository;

    @BeforeEach
    void setup() {
        users = new ArrayList<>();
        users.add(new User("User", 1, 1));

        repository = new UserRepository(users);
    }

    @Test
    void isUserExist_NullName() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.isUserExist(null);
        });

        assertEquals("Name cannot be null.", exception.getMessage());

    }

    @ParameterizedTest(name = "{index} - {0} is a name.")
    @ValueSource(strings = {"", " ", "\n\n", "\t"})
    void isUserExist_BlankName(String name) {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.isUserExist(name);
        });

        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void isUserExist_true() {

        assertTrue(repository.isUserExist("User"));

    }

    @Test
    void isUserExist_false() {

        assertFalse(repository.isUserExist("Test"));

    }

    @Test
    void userByName_Exist() {

        assertEquals(users.get(0), repository.userByName("User"));

    }

    @Test
    void userByName_NotExist() {

        assertNull(repository.userByName("Test"));

    }

    @Test
    void addNewUser_NullUser() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.addNewUser(null);
        });

        assertEquals("Incorrect value of passed parameter.", exception.getMessage());

    }

    @Test
    void addNewUser() {

        repository.addNewUser(new User("TestUser", 1, 1));
        assertEquals(2, users.size());

    }
}