package by.tc.nb.service.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import by.tc.nb.bean.entity.Note;
import by.tc.nb.bean.entity.NoteBook;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.exception.ServiceException;
import by.tc.nb.source.NoteBookProvider;

public class NoteBookServiceImpl implements NoteBookService {

	@Override
	public void addNote(String note, Date date) throws ServiceException {
		if (note == null || "".equals(note)){
			throw new ServiceException("Wrong parameter!");
		}
		Note newNote = new Note(note, date);
		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
		noteBook.addNote(newNote);
	}

	@Override
	public void createNewBook() {
		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
		noteBook.cleanNoteBook();
	}

	@Override
	public List<Note> findByDate(int day, int month, int year) throws ServiceException {
		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
		List<Note> list = new ArrayList<Note>();
		for (Note note : noteBook.getNotes()) {
			Calendar calendar = Calendar.getInstance();
			Date date = note.getDate();
			calendar.setTime(date);
			int dayNote = calendar.get(Calendar.DAY_OF_MONTH);
			int monthNote = calendar.get(Calendar.MONTH);
			System.out.println("month = " + monthNote);
			int yearNote = calendar.get(Calendar.YEAR);
			if (day != 0 && month != 0 && year != 0) {
				if (day == dayNote && month == monthNote && year == yearNote) {
					list.add(note);
				}
			} else if (day != 0 && month == 0 && year == 0) {
				if (day == dayNote) {
					list.add(note);
				}
			} else if (day == 0 && month != 0 && year == 0) {
				if (month == monthNote) {
					list.add(note);
				}
			} else if (day == 0 && month == 0 && year != 0) {
				if (year == yearNote) {
					list.add(note);
				}
			} else if (day != 0 && month != 0 && year == 0) {
				if (day == dayNote && month == monthNote) {
					list.add(note);
				}
			} else if (day != 0 && month == 0 && year != 0) {
				if (day == dayNote && year == yearNote) {
					list.add(note);
				}
			} else if (day == 0 && month != 0 && year != 0) {
				if (month == monthNote && year == yearNote) {
					list.add(note);
				}
			}
		}
		return list;
	}

	@Override
	public List<Note> findByNote(String note) {
		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
		List<Note> list = new ArrayList<Note>();
		for (Note note1 : noteBook.getNotes()) {
			if (note1.getNote().contains(note)) {
				list.add(note1);
			}
		}
		return list;
	}

	@Override
	public void readFile() throws ServiceException {
		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
		noteBook.cleanNoteBook();
		try(FileInputStream streamIn = new FileInputStream("noteBook");
			ObjectInputStream serial = new ObjectInputStream(streamIn))
		{
			Object noteBookSerial = serial.readObject();
			NoteBook book = (NoteBook) noteBookSerial;
			noteBook.setNotes(book.getNotes());
		} catch(FileNotFoundException e){
			throw new ServiceException("File not found!");
		} catch(IOException e){
			throw new ServiceException("Error output/input!");
		} catch(ClassNotFoundException e) {
			throw new ServiceException("Error output/input!");
		}
	}

	@Override
	public List<Note> showNotebook() {
		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
		List<Note> list = noteBook.getNotes();
		return list;
	}

	@Override
	public void writeFile() throws ServiceException {
		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
		try(FileOutputStream streamOut = new FileOutputStream("notebook");
			ObjectOutputStream serial  = new ObjectOutputStream(streamOut))
		{
			serial.writeObject(noteBook);
			serial.flush();
		}
		catch(IOException e){
			throw new ServiceException("Error output/input!");
		}
	}
}
