package by.tc.nb.view;

import by.tc.nb.bean.*;
import by.tc.nb.bean.entity.Note;
import by.tc.nb.controller.Controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class View {
	static final String exit = "exit";
	static final String add = "add";
	static final String create = "create";
	static final String findContent = "find";
	static final String findDate = "date";
	static final String show = "show";
	static final String writeFile = "write";
	static final String readFile = "read";

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {

			System.out.print(exit + " ");
			System.out.print(add + " ");
			System.out.print(create + " ");
			System.out.print(findContent + " ");
			System.out.print(findDate + " ");
			System.out.print(show + " ");
			System.out.print(writeFile + " ");
			System.out.println(readFile);

			Controller controller = new Controller();
			System.out.println("Enter the command");
			String string = reader.readLine();
			Response response;
			switch (string) {
				case "exit": reader.close();
					return;
				case "add": AddNoteRequest request = new AddNoteRequest();
					request.setCommandName("ADD_NEW_NOTE");
					System.out.println("Enter your note");
					String myNote = reader.readLine();
					request.setNote(myNote);
					response = controller.doRequest(request);
					if(response.isErrorStatus() ==  false){
						System.out.println(response.getErrorMessage());
					}else {
						System.out.println(response.getResultMessage());
					}
					break;
				case "show": Request request1 = new Request();
					request1.setCommandName("SHOW_NOTEBOOK");
					response = controller.doRequest(request1);
					if(response.isErrorStatus() ==  false){
						System.out.println(response.getErrorMessage());
					}else {
						ShowAllNotesResponse res = (ShowAllNotesResponse) response;
						List<Note> book = res.getAllBook();
						if (!book.isEmpty()) {
							for (Note note : book) {
								System.out.println(note);
							}
						}
						System.out.println(response.getResultMessage());
					}
					break;
				case "create": Request request2 = new Request();
					System.out.println("Are you sure you want to create a new notebook? Any unsaved " +
							"data will be lost !!! Y/N");
					String answer = reader.readLine();
					if (!(answer.equals("Y") || answer.equals("y"))) {
						break;
					}
					request2.setCommandName("CREATE_NOTEBOOK");
					response = controller.doRequest(request2);
					if(response.isErrorStatus() ==  false){
						System.out.println(response.getErrorMessage());
					}else {
						System.out.println(response.getResultMessage());
					}
					break;
				case "write": Request request4 = new Request();
					request4.setCommandName("WRITE_FILE");
					response = controller.doRequest(request4);
					if(response.isErrorStatus() ==  false){
						System.out.println(response.getErrorMessage());
					}else {
						System.out.println(response.getResultMessage());
					}
					break;
				case "read": Request request5 = new Request();
					request5.setCommandName("READ_FILE");
					response = controller.doRequest(request5);
					if(response.isErrorStatus() ==  false){
						System.out.println(response.getErrorMessage());
					}else {
						System.out.println(response.getResultMessage());
					}
					break;
				case "find": FindNotesRequest request3 = new FindNotesRequest();
					System.out.println("Enter the search string!");
					String search = reader.readLine();
					request3.setFindString(search);
					request3.setCommandName("FIND_BY_NOTE");
					response = controller.doRequest(request3);
					if(response.isErrorStatus() ==  false){
						System.out.println(response.getErrorMessage());
					}else {
						FindNotesResponse res = (FindNotesResponse) response;
						List<Note> noteFind = res.getFindBook();
						if (!noteFind.isEmpty()) {
							for (Note note : noteFind) {
								System.out.println(note);
							}
						}
						System.out.println(response.getResultMessage());
					}
					break;
				case "date": FindByDateRequest request6 = new FindByDateRequest();
					System.out.println("Enter day! Example 29");
					request6.setDay(reader.readLine());
					System.out.println("Enter month! Example 8");
					request6.setMonth(reader.readLine());
					System.out.println("Enter year! Example 2016");
					request6.setYear(reader.readLine());
					request6.setCommandName("FIND_BY_DATE");
					response = controller.doRequest(request6);
					if(response.isErrorStatus() ==  false){
						System.out.println(response.getErrorMessage());
					}else {
						FindByDateResponse res = (FindByDateResponse) response;
						List<Note> noteFind = res.getDateNotes();
						if (!noteFind.isEmpty()) {
							for (Note note : noteFind) {
								System.out.println(note);
							}
						}
						System.out.println(response.getResultMessage());
					}
					break;
				default: System.out.println("Incorrect command!");
					break;
			}
		}
	}
}

