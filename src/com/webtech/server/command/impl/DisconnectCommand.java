package com.webtech.server.command.impl;

import com.webtech.server.command.ICommand;
import com.webtech.server.command.exception.CommandException;
import com.webtech.server.model.AuthenticationType;
import com.webtech.server.service.ServiceFactory;

public class DisconnectCommand implements ICommand {
    public String execute(Object caller, String request) throws CommandException {
        ServiceFactory.getInstance().getAuthService().setAuthType(caller, AuthenticationType.UNAUTHORIZED);
        return "Bye bye!";
    }
}
