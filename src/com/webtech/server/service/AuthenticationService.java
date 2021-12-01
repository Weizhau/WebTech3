package com.webtech.server.service;

import com.webtech.server.model.AuthenticationType;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {

    private static final AuthenticationService INSTANCE = new AuthenticationService();
    private final Map<Object, AuthenticationType> users;

    private AuthenticationService() {
        users = new HashMap<>();
    }

    public static AuthenticationService getInstance() {
        return INSTANCE;
    }

    public AuthenticationType getAuthType(Object user) {
        if (!users.containsKey(user)) {
            users.put(user, AuthenticationType.UNAUTHORIZED);
        }

        return users.get(user);
    }

    public void setAuthType(Object user, AuthenticationType type) {
        users.put(user, type);
    }
}

