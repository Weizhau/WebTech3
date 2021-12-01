package com.webtech.server.command;

import com.webtech.server.command.exception.CommandException;

public interface ICommand {
    String execute(Object caller, String request) throws CommandException;
}
