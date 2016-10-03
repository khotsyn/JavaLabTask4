package by.tc.nb.bean;

public class FindNotesRequest extends Request {

    private String findString;

    public String getFindString() {
        return findString;
    }

    public void setFindString(String findString) {
        this.findString = findString;
    }
}