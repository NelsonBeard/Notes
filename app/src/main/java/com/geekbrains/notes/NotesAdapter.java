package com.geekbrains.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Notes> notesList;

    public NotesAdapter(List<Notes> notesList) {
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_view_item_layout, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {
        holder.bind(notesList.get(position));
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView headLine = itemView.findViewById(R.id.headline);

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(Notes note) {
            headLine.setText(note.getHeadline());
        }
    }
}
