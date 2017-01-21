package com.marek.simplenotes;


public class Note {

    private long noteId;
    private String content;
    private String date;

    public Note(long noteId ,String content, String date) {
        this.noteId = noteId;
        this.content = content;
        this.date = date;
    }

    public Note(){}

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
