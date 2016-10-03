package by.tc.nb.command.impl;

import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.bean.ShowAllNotesResponse;
import by.tc.nb.bean.entity.NoteBook;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.source.NoteBookProvider;

public class ShowNoteBook implements Command {

    @Override
    public Response execute(Request request) throws CommandException {

        NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
        Response response = new ShowAllNotesResponse();
        ShowAllNotesResponse res;
        if (response instanceof ShowAllNotesResponse) {
            res = (ShowAllNotesResponse) response;

        } else {
            throw new CommandException("Wrong response");
        }
        ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();
        res.setAllBook(nbService.showNotebook());
        res.setErrorStatus(true);
        if (res.getAllBook().isEmpty()) {
            res.setResultMessage("Notebook is empty!");
        } else {
            res.setResultMessage("All OK!");
        }
        return res;
    }
}