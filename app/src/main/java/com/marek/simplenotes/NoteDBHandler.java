package com.marek.simplenotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteDBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NOTES = "notes";
    public static final String COLUMN_ID = "noteId";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_DATE = "date";

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NOTES + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_CONTENT + " TEXT, " +
            COLUMN_DATE + " NUMERIC " +
            ")";

    public NoteDBHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NOTES);
        db.execSQL(TABLE_CREATE);
    }

}
