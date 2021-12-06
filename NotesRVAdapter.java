package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesRVAdapter extends RecyclerView.Adapter<NotesRVAdapter.ViewHolder> {
    private List<Note> notes=new ArrayList<>();
    private onItemClickListener listener;
    public Context context;


    @NonNull
    @Override
    public NotesRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NotesRVAdapter.ViewHolder holder, int position) {
        Note currentNote=notes.get(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDesc.setText(currentNote.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes){
        this.notes= notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position){
        return notes.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewDesc,textViewTitle,textViewPriority;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDesc=itemView.findViewById(R.id.textViewDesc);
            textViewTitle=itemView.findViewById(R.id.textViewTitle);
            textViewPriority=itemView.findViewById(R.id.textViewPriority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if(listener!=null && position!=RecyclerView.NO_POSITION){
                        listener.onItemClicked(notes.get(position));
                    }
                }
            });
        }
    }

    public interface onItemClickListener{
        void onItemClicked(Note note);
    }

    public void setOnItemClickListener(onItemClickListener listener){

        this.listener=listener;
    }

}
