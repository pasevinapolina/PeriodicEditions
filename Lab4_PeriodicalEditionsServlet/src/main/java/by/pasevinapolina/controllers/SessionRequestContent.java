package by.pasevinapolina.controllers;

import javafx.collections.ObservableList;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Objects;

/**
 *
 */
public class SessionRequestContent {

    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;

    public void extractValues(HttpServletRequest request) {

    }

    public void insertAttributes(HttpServletRequest request) {

    }
}
