package service;

import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.UserRepository;

public class StartGameService {

    private UserRepository userRepository;
    private static final Logger logger = LogManager.getLogger();

    public StartGameService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setNewGameUserData(User user) {

        if (user == null) {
            logger.error("User is null");
            throw new IllegalArgumentException("User cannot be null.");
        }

        user.setGamesCount(user.getGamesCount() + 1);
        user.setCurrentQuestionId(1);

    }

    public User getCreateUser(String name) {

        User user;
        if(userRepository.isUserExist(name)) {
            user = userRepository.userByName(name);
            setNewGameUserData(user);

            logger.debug("Пользователь [{}] начал игру", name);
        } else {
            user = new User(name, 1, 1);
            userRepository.addNewUser(user);

            logger.debug("Пользователь [{}] создан и начал игру", name);
        }

        return user;

    }
}
