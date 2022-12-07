package entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.Serializable;
import static java.util.Objects.isNull;

@Getter
@Setter
@EqualsAndHashCode
public class User implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private String name;
    private int gamesCount;
    private long currentQuestionId;

    public User(String name, int gamesCount, long currentQuestionId) {

        if (isNull(name)) {
            logger.error("Name is null");
            throw new IllegalArgumentException("Name cannot be null.");
        } else if (name.isBlank()) {
            logger.error("Name is blank");
            throw new IllegalArgumentException("Name cannot be blank.");
        }

        if (gamesCount < 0) {
            logger.error("Count of game is negative");
            throw new IllegalArgumentException("Count of game cannot be negative.");
        }
        if (currentQuestionId < 0) {
            logger.error("Current question Id is negative");
            throw new IllegalArgumentException("Current question Id cannot be negative.");
        }

        this.name = name;
        this.gamesCount = gamesCount;
        this.currentQuestionId = currentQuestionId;

    }
}
