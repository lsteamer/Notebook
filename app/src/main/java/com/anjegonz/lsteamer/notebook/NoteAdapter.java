package com.anjegonz.lsteamer.notebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lstea on 27/5/2016.
 */
public class NoteAdapter extends ArrayAdapter<Note> {

    public static class ViewHolder{
        TextView title;
        TextView note;
        ImageView noteIcon;
    }
    public NoteAdapter(Context context, ArrayList<Note> notes){
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Get the data item for this position
        Note note = getItem(position);

        //Create a viewholder
        ViewHolder viewHolder;

        // Checks if an existing view is being reused, otherwise, inflate a new view from custom row layout.
        if (convertView == null){
            //if there isn't a view that's being used, create one and makes sure to create a viewholder along with it to save our view references to
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);

            //Grab references of views so we can populate them with specific note row data
            viewHolder.title = (TextView) convertView.findViewById(R.id.listItemNoteTitle);
            viewHolder.note = (TextView) convertView.findViewById(R.id.listItemNoteBody);
            viewHolder.noteIcon = (ImageView) convertView.findViewById(R.id.listItemNoteImg);
            convertView.setTag(viewHolder);

        }else{
            //a view exists, so just grab the viewHolder and grab the widgets from it
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Fill each new reference view with the data associated with the note its' referencing.
        viewHolder.title.setText(note.getTitle());
        viewHolder.note.setText(note.getMessage());
        viewHolder.noteIcon.setImageResource(note.getAssociatedDrawable());


        //now that we modified the view to display the appropriate data, return it so it will be displayed.
        return convertView;
    }

}
