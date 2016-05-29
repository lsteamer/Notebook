package com.anjegonz.lsteamer.notebook;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
        //Long press on something
        registerForContextMenu(getListView());

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);
        launchNoteDetailActivity(MainActivity.FragmentToLaunch.VIEW, position);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        //Get the position of the note that has been long pressed, place it in Info
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int rowPosition = info.position;

        //returns to us the id of whatever menu item we selected
        switch (item.getItemId()){
            case R.id.edit:
                launchNoteDetailActivity(MainActivity.FragmentToLaunch.EDIT, rowPosition);
                return true;

        }

        return super.onContextItemSelected(item);
    }

    private void launchNoteDetailActivity(MainActivity.FragmentToLaunch ftl, int position){
        //Grab the note information associated with whatever note item we clicked on
        Note note = (Note) getListAdapter().getItem(position);
        //Create a new intent that launches our NoteDetailActivity
        Intent intent = new Intent (getActivity(), NoteDetailActivity.class);
        //Pass along the information of the note we clicked on to our NoteDetailActivity
        intent.putExtra(MainActivity.NOTE_TITLE_EXTRA, note.getTitle());
        intent.putExtra(MainActivity.NOTE_MESSAGE_EXTRA, note.getMessage());
        intent.putExtra(MainActivity.NOTE_CATEGORY_EXTRA, note.getCategory());
        intent.putExtra(MainActivity.NOTE_ID_EXTRA, note.getId());
        switch(ftl) {
            case VIEW:
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, MainActivity.FragmentToLaunch.VIEW);
                break;
            case EDIT:
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, MainActivity.FragmentToLaunch.EDIT);
                break;
        }
        //Start the activity
        startActivity(intent);
    }

}
