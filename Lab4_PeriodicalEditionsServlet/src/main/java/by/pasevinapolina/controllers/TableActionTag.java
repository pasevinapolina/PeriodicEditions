package by.pasevinapolina.controllers;

import by.pasevinapolina.models.ClientType;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Custom tag that allows admin to edit and delete table rows
 * @author Polina Pasevina
 * @version 1.0
 */
public class TableActionTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {

        ClientType currentClient = (ClientType)pageContext.getSession().getAttribute("userType");
        if(currentClient == ClientType.ADMIN) {
            try {
                JspWriter out = pageContext.getOut();
                out.write("<td><a class=\"glyphicon glyphicon-edit\" >Редактировать</a> |\n" +
                        " <a class=\"glyphicon glyphicon-trash\">Удалить</a></td>\n");
            } catch (IOException e) {
                throw new JspException(e.getMessage());
            }
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
