package by.tc.nb.command.impl;

import by.tc.nb.bean.FindNotesRequest;
import by.tc.nb.bean.FindNotesResponse;
import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.bean.entity.Note;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import java.util.ArrayList;
import java.util.List;

public class FindNotes implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		Response response = new FindNotesResponse();
		FindNotesResponse res;
		if (response instanceof FindNotesResponse) {
			res = (FindNotesResponse) response;
		} else {
			throw new CommandException("Wrong response");
		}
		FindNotesRequest req;
		if (request instanceof FindNotesRequest) {
			req = (FindNotesRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}
		List<Note> list = new ArrayList<Note>();
		String noteRec = req.getFindString();
		ServiceFactory service = ServiceFactory.getInstance();
		NoteBookService nbService = service.getNoteBookService();
		list = nbService.findByNote(noteRec);

		res.setErrorStatus(true);
		if (list.isEmpty()) {
			res.setResultMessage("There is no notes matched your request");
		} else {
			res.setFindBook(list);
			res.setResultMessage("All OK!");
		}
		return res;
	}
}

