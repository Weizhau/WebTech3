package com.webtech.server.command.impl;

import com.webtech.server.command.ICommand;
import com.webtech.server.command.exception.CommandException;
import com.webtech.server.model.AuthenticationType;
import com.webtech.server.service.ServiceFactory;

public class CreateCommand implements ICommand {
    public String execute(Object caller, String request) throws CommandException {
        String[] arguments = request.split(" ");
        if (arguments.length != 3) throw new CommandException("CREATE invalid syntax");

        if (ServiceFactory.getInstance().getAuthService().getAuthType(caller) != AuthenticationType.MANAGER)
            return "Should be MANAGER";

        ServiceFactory.getInstance().getCaseService().addPerson(arguments[1], arguments[2]);
        return "Success";
    }
}
