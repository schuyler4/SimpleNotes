package com.marek.simplenotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import java.util.Date;
import android.content.Intent;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {

    private static final String ID ="ID";
    private static final String USAGE = "USAGE";
    private static final String ADD = "ADD";
    private static final String UPDATE = "UPDATE";
    EditText noteTextInput;
    Note newNote;
    NoteOperations noteData;
    String usage;
    String id;
    Note oldNote;
    TextView warningText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        warningText = (TextView)findViewById(R.id.warningText);

        noteTextInput = (EditText)findViewById(R.id.noteText);
        newNote = new Note();
        noteData = new NoteOperations(this);
        noteData.open();

        usage = getIntent().getStringExtra(USAGE);
        id = getIntent().getStringExtra(ID);

        if(usage.equals(UPDATE)) {
            oldNote = noteData.getNote(Long.parseLong(id));
            noteTextInput.setText(oldNote.getContent());
        }

        noteTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warningText.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    public void doneOnClick(View view) {
        String noteText = noteTextInput.getText().toString();
        String noteDate = new Date().toString();

        newNote.setContent(noteText);
        newNote.setDate(noteDate);

        if(noteText.length() > 0) {
            if (usage.equals(ADD)) {
                noteData.addNote(newNote);
            } else if (usage.equals(UPDATE)) {
                oldNote.setContent(noteText);
                oldNote.setDate(noteDate);

                noteData.updateNote(oldNote);
            }

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            warningText.setText(R.string.warning_text);
        }
    }

    public void noOnClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
