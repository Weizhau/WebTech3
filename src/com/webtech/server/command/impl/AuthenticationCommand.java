package com.webtech.server.command.impl;

import com.webtech.server.command.ICommand;
import com.webtech.server.command.exception.CommandException;
import com.webtech.server.model.AuthenticationType;
import com.webtech.server.service.ServiceFactory;

public class AuthenticationCommand implements ICommand {
    @Override
    public String execute(Object caller, String request) throws CommandException {
        String[] arguments = request.split(" ");
        if (arguments.length != 2) throw new CommandException("AUTH command should contain 1 argument");
        AuthenticationType authenticationType;
        try {
            authenticationType = AuthenticationType.valueOf(arguments[1]);
        } catch (IllegalArgumentException e) {
            throw new CommandException("No such auth type");
        }

        ServiceFactory.getInstance().getAuthService().setAuthType(caller, authenticationType);
        return "Success.";
    }
}
