package by.tc.nb.bean.entity;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {

    private String note;
    private Date date;

    public Note(String note) {
        this.note = note;
        date = new Date();
    }

    public Note(String note, Date date) {
        this.note = note;
        this.date = date;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        String note = this.note + " " + date.toString();
        return note;
    }

}
