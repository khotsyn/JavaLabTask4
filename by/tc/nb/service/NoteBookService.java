package by.tc.nb.service;

import java.util.Date;
import java.util.List;
import by.tc.nb.bean.entity.Note;
import by.tc.nb.service.exception.ServiceException;

public interface NoteBookService {
	
	void addNote(String note, Date date) throws ServiceException;
	void createNewBook();
	List<Note> findByDate(int day, int month, int year) throws ServiceException;
	List<Note> findByNote(String note);
	void readFile() throws ServiceException;
	List<Note> showNotebook();
	void writeFile() throws ServiceException;
}
