package by.pasevinapolina.utils;

/**
 * DAO layer exceptions
 * @see java.lang.Exception
 * @author Polina Pasevina
 * @version 1.0
 */
public class DAOException extends Exception {
    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
