package com.anjegonz.lsteamer.notebook;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NoteDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        createAndAddFragment();
    }

    private void createAndAddFragment(){

        //Manager that manages fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //Creating a fragment
        NoteViewFragment noteViewFragment = new NoteViewFragment();
        setTitle(R.string.viewFragmentTitle);

        //adding it - 1: Layout container (id in XML), 2: put this inside 3:Tag in case you want to retrieve the fragment
        fragmentTransaction.add(R.id.note_container, noteViewFragment, "NOTE_VIEW_FRAGMENT");
        //Make it happen.
        fragmentTransaction.commit();

    }
}
