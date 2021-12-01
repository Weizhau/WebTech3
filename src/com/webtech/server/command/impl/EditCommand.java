package com.webtech.server.command.impl;

import com.webtech.server.command.ICommand;
import com.webtech.server.command.exception.CommandException;
import com.webtech.server.model.AuthenticationType;
import com.webtech.server.service.ServiceFactory;

public class EditCommand implements ICommand {
    public String execute(Object caller, String request) throws CommandException {
        String[] arguments = request.split(" ");
        if (arguments.length != 4) throw new CommandException("Invalid syntax EDIT");

        if (ServiceFactory.getInstance().getAuthService().getAuthType(caller) != AuthenticationType.MANAGER)
            return "Should be MANAGER";

        int id;
        try {
            id = Integer.parseInt(arguments[1]);
        } catch (NumberFormatException ignored) {
            return "Invalid id";
        }

        if (!ServiceFactory.getInstance().getCaseService().containsPerson(id))
            return "No such case";

        ServiceFactory.getInstance().getCaseService().editPerson(id, arguments[2], arguments[3]);
        return "Success";
    }
}
