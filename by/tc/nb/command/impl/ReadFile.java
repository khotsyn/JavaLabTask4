package by.tc.nb.command.impl;

import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.exception.ServiceException;

public class ReadFile implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();
        try {
            nbService.readFile();
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage());
        }
        Response response = new Response();
        response.setErrorStatus(true);
        response.setResultMessage("Notebook was written from file!");
        return response;
    }
}
