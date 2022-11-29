package entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {
    User user;

    @Test
    void nullNameConstructorTest() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            user = new User(null, 1, 1);
        });

        assertEquals("Name cannot be null.", exception.getMessage());

    }

    @ParameterizedTest(name = "{index} - {0} is a name.")
    @ValueSource(strings = {"", " ", "\n\n", "\t"})
    void emptyNameConstructorTest(String name) {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            user = new User(name, 1, 1);
        });

        assertEquals("Name cannot be blank.", exception.getMessage());

    }

    @Test
    void negativeGamesCountConstructorTest() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            user = new User("Name", -5, 1);
        });

        assertEquals("Count of game cannot be negative.", exception.getMessage());

    }

    @Test
    void negativeCurrentQuestionIdConstructorTest() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            user = new User("Name", 1, -10);
        });

        assertEquals("Current question Id cannot be negative.", exception.getMessage());

    }

}