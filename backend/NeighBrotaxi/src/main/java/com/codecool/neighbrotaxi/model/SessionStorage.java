package com.codecool.neighbrotaxi.model;

import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class SessionStorage {
    private List<String> errorMessages;
    private List<String> infoMessages;
    private User loggedInUser;

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void addErrorMessage(String errorMessage) {
        this.errorMessages.add(errorMessage);
    }

    public void clearAllErrorMessages() {
        this.errorMessages.clear();
    }

    public List<String> getInfoMessages() {
        return infoMessages;
    }

    public void setInfoMessages(List<String> infoMessages) {
        this.infoMessages = infoMessages;
    }

    public void addInfoMessage(String infoMessage) {
        this.infoMessages.add(infoMessage);
    }

    public void clearAllInfoMessages() {
        this.infoMessages.clear();
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
