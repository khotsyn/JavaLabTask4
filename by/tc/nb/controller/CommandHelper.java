package by.tc.nb.controller;

import java.util.HashMap;
import java.util.Map;
import by.tc.nb.command.Command;
import by.tc.nb.command.impl.*;

public class CommandHelper {

	private Map<String, Command> commands = new HashMap<String, Command>();

	public CommandHelper() {
		commands.put("ADD_NEW_NOTE", new AddNewNote());
		commands.put("FIND_BY_NOTE", new FindNotes());
		commands.put("SHOW_NOTEBOOK", new ShowNoteBook());
		commands.put("CREATE_NOTEBOOK", new CreateBook());
		commands.put("WRITE_FILE", new WriteFile());
		commands.put("READ_FILE", new ReadFile());
		commands.put("FIND_BY_DATE", new FindByDate());
	}

	public Command getCommand(String commandName) {
		Command command;
		command = commands.get(commandName);
		return command;
	}
}
