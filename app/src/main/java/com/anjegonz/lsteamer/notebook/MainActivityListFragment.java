package com.anjegonz.lsteamer.notebook;


import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityListFragment extends ListFragment {

    private ArrayList<Note> notes;
    private NoteAdapter noteAdapter;
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);


        /*
        String[] values = new String[]{"Xbox One","PS4","WiiU",
                                        "Xbox 360","PS3","Wii",
                                        "Xbox","PS2","GameCube"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);
        */

        notes = new ArrayList<Note>();
        notes.add(new Note("This is a new Note title","This is the body of the note",Note.Category.PERSONAL));
        notes.add(new Note("All of them really","An Original body for every original note",Note.Category.FINANCE));
        notes.add(new Note("How do you do","I'm doing alright. How about you?",Note.Category.TECHNICAL));
        notes.add(new Note("Original Title","AS it turns out, this is ",Note.Category.PERSONAL));
        notes.add(new Note("Wat","All of my wats",Note.Category.QUOTE));
        notes.add(new Note("Und was?","Ich kann noch kein Deutsch sprechen",Note.Category.QUOTE));
        notes.add(new Note("Un título","Como el que no tengo en mi casa",Note.Category.PERSONAL));
        notes.add(new Note("Mis cocos","Me gustan mucho los cocos y a ti?",Note.Category.TECHNICAL));
        notes.add(new Note("El último","es difícil ser original en el cuerpo de todos y cada uno.",Note.Category.FINANCE));

        noteAdapter = new NoteAdapter(getActivity(),notes);

        setListAdapter(noteAdapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);
    }


}
