package com.webtech.server.command.impl;

import com.webtech.server.command.ICommand;
import com.webtech.server.command.exception.CommandException;
import com.webtech.server.model.AuthenticationType;
import com.webtech.server.model.Person;
import com.webtech.server.service.ServiceFactory;

import java.util.List;

public class ViewCommand implements ICommand {
    public String execute(Object caller, String request) throws CommandException {
        if (ServiceFactory.getInstance().getAuthService().getAuthType(caller) == AuthenticationType.UNAUTHORIZED)
            return "Should be authenticated";

        List<Person> people = ServiceFactory.getInstance().getCaseService().getAll();
        return toOutput(people);
    }

    private static String toOutput(List<Person> people) {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("[\n");
        for (var box : people) {
            resultBuilder.append("\t").append(box.toString()).append("\n");
        }
        resultBuilder.append("]");
        return resultBuilder.toString();
    }
}
