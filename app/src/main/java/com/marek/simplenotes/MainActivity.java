package com.marek.simplenotes;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    NoteOperations noteOps;
    List<Note> notes;
    List<String> noteContents;
    ListView noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteList = getListView();

        noteOps = new NoteOperations(this);
        noteOps.open();
        notes = noteOps.getAllNotes();
        noteOps.close();

        noteContents = new ArrayList();
        for(int i = 0; i < notes.size(); i++) {
            noteContents.add(notes.get(i).getContent());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, noteContents);
        setListAdapter(adapter);

        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NewActivity.class);
                intent.putExtra("ID", Long.toString(notes.get(position).getNoteId()));
                intent.putExtra("USAGE", "UPDATE");

                startActivity(intent);
            }
        });
    }

    public void newButtonOnClick(View view) {
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra("ID", 0);
        intent.putExtra("USAGE", "ADD");
        startActivity(intent);
    }

}
