package com.geekbrains.notes;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private NoteSource source;
    private OnNoteClickListener clickListener;
    private final Fragment fragment;
    private int menuPosition = -1;

    public NotesAdapter(Fragment fragment, NoteSource source) {

        this.source = source;
        this.fragment = fragment;
    }

    public void setClickListener(OnNoteClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public int getMenuPosition() {
        return menuPosition;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_view_item_layout, parent, false)
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {
        holder.bind(source.getNote(position));
    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView headLine = itemView.findViewById(R.id.headline);

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            fragment.registerForContextMenu(itemView);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        void bind(Note note) {

            headLine.setText(note.getHeadline());
            headLine.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onNoteClick(v, getAdapterPosition());
                }
            });

            headLine.setOnLongClickListener(v -> {
                menuPosition = getLayoutPosition();
                itemView.showContextMenu();
                return true;
            });
        }
    }

    interface OnNoteClickListener {
        void onNoteClick(View view, int position);
    }
}
