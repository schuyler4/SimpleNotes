package com.marek.simplenotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.util.Date;
import android.content.Intent;

public class NewActivity extends AppCompatActivity {

    private static final String ID ="ID";
    private static final String USAGE = "USAGE";
    EditText noteTextInput;
    Note newNote;
    NoteOperations noteData;
    String usage;
    String id;
    Note oldNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        noteTextInput = (EditText)findViewById(R.id.noteText);
        newNote = new Note();
        noteData = new NoteOperations(this);
        noteData.open();

        usage = getIntent().getStringExtra(USAGE);
        id = getIntent().getStringExtra(ID);

        if(usage.equals("UPDATE")) {
            oldNote = noteData.getNote(Long.parseLong(id));
            noteTextInput.setText(oldNote.getContent());
        }
    }

    public void doneOnClick(View view) {
        String noteText = noteTextInput.getText().toString();
        String noteDate = new Date().toString();

        newNote.setContent(noteText);
        newNote.setDate(noteDate);

        if(usage.equals("ADD")) {
            noteData.addNote(newNote);
        }
        else if(usage.equals("UPDATE")) {
            oldNote.setContent(noteText);
            oldNote.setDate(noteDate);

            noteData.updateNote(oldNote);
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
