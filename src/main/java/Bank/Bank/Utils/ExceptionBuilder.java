package Bank.Bank.Utils;

import org.springframework.stereotype.Component;

@Component
public class ExceptionBuilder {
    public static String BuildSelectAllException(String className) {
        return String.format("No %s exists yet", className);
    }

    public static String BuildSelectByIdException(String className) {
        return String.format("There is no %s with that ID", className);
    }

    public static String BuildInsertException(String className) {
        return String.format("The %s with that ID already exists; %s insertion cancelled", className, className);
    }

    public static String BuildUpdateException(String className) {
        return String.format("The %s with that ID doesn´t exist; %s update cancelled", className, className);
    }

    public static String BuildDeleteException(String className) {
        return String.format("The %s with that ID doesn´t exist; %s deletion cancelled", className, className);
    }
}
