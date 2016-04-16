package by.pasevinapolina.commands;

import by.pasevinapolina.dao.EditionDao;
import by.pasevinapolina.dao.impl.EditionDaoImpl;
import by.pasevinapolina.models.Edition;
import by.pasevinapolina.utils.ConfigurationManager;
import by.pasevinapolina.utils.DAOException;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Locale;

/**
 * Command to register new edition
 */
public class AddEditionCommand implements ActionCommand {

    private EditionDao editionDao;

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;
        Edition edition = null;
        String author = null;
        try {
            author = request.getParameter("author");
            String name = request.getParameter("name");
            int outFreq = Integer.parseInt(request.getParameter("outFreq"));

            EntityManager entityManager = (EntityManager) request.getServletContext().getAttribute("em");
            editionDao = new EditionDaoImpl(entityManager);
            edition = editionDao.createEdition(author, name, outFreq);

        } catch (DAOException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if(edition != null)
            page = new EditionCommand().execute(request);
        return page;
    }
}
