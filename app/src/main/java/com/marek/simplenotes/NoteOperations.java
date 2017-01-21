package com.marek.simplenotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;


public class NoteOperations {

    SQLiteOpenHelper handler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
        NoteDBHandler.COLUMN_ID,
        NoteDBHandler.COLUMN_CONTENT,
        NoteDBHandler.COLUMN_DATE,
    };

    public NoteOperations(Context context) {
        handler = new NoteDBHandler(context);
    }

    public void open() {
        database = handler.getWritableDatabase();
    }

    public void close() {
        handler.close();
    }

    public Note addNote(Note Note) {
        ContentValues values  = new ContentValues();
        values.put(NoteDBHandler.COLUMN_CONTENT,Note.getContent());
        values.put(NoteDBHandler.COLUMN_DATE, Note.getDate());
        long insertId = database.insert(NoteDBHandler.TABLE_NOTES,null,values);
        Note.setNoteId(insertId);
        return Note;
    }

    public Note getNote(long id) {

        Cursor cursor = database.query(NoteDBHandler.TABLE_NOTES,allColumns,NoteDBHandler.COLUMN_ID
                + "=?",new String[]{String.valueOf(id)},null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

            Note note = new Note(Long.parseLong(cursor.getString(0)),cursor.getString(1),
                    cursor.getString(2));

            cursor.close();
        return note;
    }

    public List<Note> getAllNotes() {

        Cursor cursor = database.query(NoteDBHandler.TABLE_NOTES,allColumns,null,null,null, null,
                null);

        List<Note> notes = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Note note = new Note();
                note.setNoteId(cursor.getLong(cursor.getColumnIndex(NoteDBHandler.COLUMN_ID)));
                note.setDate(cursor.getString(cursor.getColumnIndex(NoteDBHandler.COLUMN_DATE)));
                note.setContent(cursor.getString(cursor.getColumnIndex(
                        NoteDBHandler.COLUMN_CONTENT
                )));
                notes.add(note);
            }
        }

        cursor.close();
        return notes;
    }


    public int updateNote(Note note) {

        ContentValues values = new ContentValues();
        values.put(NoteDBHandler.COLUMN_CONTENT, note.getContent());
        values.put(NoteDBHandler.COLUMN_DATE, note.getDate());

        // updating row
        return database.update(NoteDBHandler.TABLE_NOTES, values,
                NoteDBHandler.COLUMN_ID + "=?",new String[] { String.valueOf(note.getNoteId())});
    }

}
