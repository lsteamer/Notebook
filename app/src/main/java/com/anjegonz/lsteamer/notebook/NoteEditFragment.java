package com.anjegonz.lsteamer.notebook;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteEditFragment extends Fragment {

    private ImageButton noteCatButton;
    private EditText title, message;
    private Note.Category savedButtonCategory;
    private AlertDialog categoryDialogObject, confirmDialogObject;
    private int catcat;

    private static final String MODIFIED_CATEGORY = "Modified Category";
    public NoteEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //INflate our fragment edit layout
        View fragmentLayout = inflater.inflate(R.layout.fragment_note_edit, container, false);


        //Grab widget references from layout
        title = (EditText) fragmentLayout.findViewById(R.id.editNoteTitle);
        message = (EditText) fragmentLayout.findViewById(R.id.editNoteMessage);
        noteCatButton = (ImageButton) fragmentLayout.findViewById(R.id.editNoteButton);
        Button savedButton = (Button) fragmentLayout.findViewById(R.id.saveNote);

        //inflate widget with data
        Intent intent = getActivity().getIntent();

        if(savedInstanceState != null){
            savedButtonCategory = (Note.Category) savedInstanceState.get(MODIFIED_CATEGORY);
        }
        else{
            savedButtonCategory = (Note.Category) intent.getSerializableExtra(MainActivity.NOTE_CATEGORY_EXTRA);
        }
        title.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE_EXTRA));
        message.setText(intent.getExtras().getString(MainActivity.NOTE_MESSAGE_EXTRA));
        //We need to receive the enum in Note. Not a String

        noteCatButton.setImageResource(Note.categoryToDrawable(savedButtonCategory));
        catcat = 0;
        switch(savedButtonCategory.toString()){
            case "PERSONAL":
                catcat = 0;
                break;
            case "TECHNICAL":
                catcat = 1;
                break;
            case "QUOTE":
                catcat = 2;
                break;
            case "FINANCE":
                catcat = 3;
                break;
        }


        // Inflate the layout for this fragment
        buildCategoryDialog();
        buildConfigDialog();

        noteCatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                categoryDialogObject.show();
            }
        });

        savedButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                confirmDialogObject.show();
            }
        });
        return fragmentLayout;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(MODIFIED_CATEGORY, savedButtonCategory);
    }

    private void buildCategoryDialog(){

        final String[] categories = new String[]{"Personal","Technical","Quote","Finance"};
        AlertDialog.Builder categoryBuilder = new AlertDialog.Builder(getActivity());
        categoryBuilder.setTitle("Choose Note Type");

        categoryBuilder.setSingleChoiceItems(categories, catcat, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                //dismisses our dialog window
                categoryDialogObject.cancel();
                switch(item) {
                    case 0:
                        savedButtonCategory = Note.Category.PERSONAL;
                        noteCatButton.setImageResource(R.drawable.p);
                        break;
                    case 1:
                        savedButtonCategory = Note.Category.TECHNICAL;
                        noteCatButton.setImageResource(R.drawable.t);
                        break;
                    case 2:
                        savedButtonCategory = Note.Category.QUOTE;
                        noteCatButton.setImageResource(R.drawable.q);
                        break;
                    case 3:
                        savedButtonCategory = Note.Category.FINANCE;
                        noteCatButton.setImageResource(R.drawable.f);
                        break;
                }
            }
        });
        categoryDialogObject = categoryBuilder.create();
    }

    private void buildConfigDialog(){
        AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(getActivity());
        confirmBuilder.setTitle("Are you sure?");
        confirmBuilder.setMessage("Are you sure you want to save the changes?");

        confirmBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("Save Note", " Note title: " + title.getText() + " Note message: " + message.getText() + " Note category: " + savedButtonCategory);
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
        confirmBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing here
            }
        });

        confirmDialogObject = confirmBuilder.create();

    }

}
