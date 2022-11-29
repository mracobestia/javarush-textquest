package repository;

import entity.User;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Objects.isNull;
@NoArgsConstructor
public class UserRepository {

    public static final String NULL_NAME_ERROR = "Name cannot be null.";
    public static final String LOG_NULL_NAME_ERROR = "Error with parameter 'name'. It cannot be null.";
    public static final String EMPTY_NAME_ERROR = "Name cannot be blank.";
    public static final String LOG_EMPTY_NAME_ERROR = "Error with parameter 'name'. It cannot be blank.";
    private List<User> users = new CopyOnWriteArrayList<>();
    private static final Logger logger = LogManager.getLogger();

    public UserRepository(List<User> users) {
        this.users = users;
    }
    public boolean isUserExist(String name) {

        userNameValidate(name);

        boolean isUserExist = false;

        List<User> usersByName = users.stream()
                .filter(user -> name.equals(user.getName()))
                .toList();

        if (!usersByName.isEmpty()) {
            isUserExist = true;
        }

        return isUserExist;
    }

    public User userByName(String name) {

        userNameValidate(name);

        User userByName = null;

        List<User> usersByName = users.stream()
                .filter(user -> name.equals(user.getName()))
                .toList();

        if (!usersByName.isEmpty()) {
            userByName =  usersByName.get(0);
        }

        return userByName;
    }

    public void addNewUser(User user) {

        if (isNull(user)) {
            logger.error("Null cannot be added as a user.");
            throw new IllegalArgumentException("Incorrect value of passed parameter.");
        }
        users.add(user);
    }

    private void userNameValidate(String name) {

        if (isNull(name)) {
            logger.error(LOG_NULL_NAME_ERROR);
            throw new IllegalArgumentException(NULL_NAME_ERROR);
        } else if (name.isBlank()) {
            logger.error(LOG_EMPTY_NAME_ERROR);
            throw new IllegalArgumentException(EMPTY_NAME_ERROR);
        }

    }
}
