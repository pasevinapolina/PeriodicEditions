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

    private String deleteModalId;
    private String editModalId;
    private String delClass;
    private String editClass;

    /**
     * Sets new modal id for delete action
     * @param deleteModalId Modal id
     */
    public void setDeleteModalId(String deleteModalId) {
        this.deleteModalId = deleteModalId;
    }

    /**
     * Sets new modal id for edit action
     * @param editModalId Modal id
     */
    public void setEditModalId(String editModalId) {
        this.editModalId = editModalId;
    }

    /**
     * Sets class for delete action
     * @param delClass Class for link to delete
     */
    public void setDelClass(String delClass) {
        this.delClass = delClass;
    }

    /**
     * Sets class for edit action
     * @param editClass Class for link to edit
     */
    public void setEditClass(String editClass) {
        this.editClass = editClass;
    }

    @Override
    public int doStartTag() throws JspException {

        ClientType currentClient = (ClientType)pageContext.getSession().getAttribute("userType");
        if(currentClient == ClientType.ADMIN) {
            try {
                JspWriter out = pageContext.getOut();
                out.write("<td><a class=\"glyphicon glyphicon-edit " + editClass + "\" " +
                        " data-toggle=\"modal\" data-target=\"#" + editModalId + "\">Редактировать</a> |\n" +
                        " <a class=\"glyphicon glyphicon-trash " + delClass + "\" " +
                        " data-toggle=\"modal\" data-target=\"#" + deleteModalId + "\">Удалить</a></td>\n");
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
