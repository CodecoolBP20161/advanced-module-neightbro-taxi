package com.codecool.neighbrotaxi.model;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SerializableSessionStorage {
    private List<String> errorMessages;
    private List<String> infoMessages;
    private User loggedInUser;

    public SerializableSessionStorage() {
        super();
    }

    public SerializableSessionStorage(SessionStorage sessionStorage) {
        this.errorMessages = sessionStorage.getErrorMessages();
        this.loggedInUser = sessionStorage.getLoggedInUser();
        this.infoMessages = sessionStorage.getInfoMessages();
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public List<String> getInfoMessages() {
        return infoMessages;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
