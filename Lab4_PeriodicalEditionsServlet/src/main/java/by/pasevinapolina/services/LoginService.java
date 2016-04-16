package by.pasevinapolina.services;

import by.pasevinapolina.dao.ReaderDao;
import by.pasevinapolina.dao.impl.ReaderDaoImpl;
import by.pasevinapolina.utils.DAOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Login service
 * @author Polina Pasevina
 * @version 1.0
 */
public class LoginService {

    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "123";
    private ReaderDao readerDao;

    public LoginService() {
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PeriodicEditionJPA");
//        try {
//            readerDao = new ReaderDaoImpl(factory);
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
    }

    public static boolean checkLogin(String enterLogin, String enterPassword) {

        return ADMIN_LOGIN.equals(enterLogin) && ADMIN_PASSWORD.equals(enterPassword);
    }
}
