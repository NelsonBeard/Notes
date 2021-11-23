package com.geekbrains.notes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class NotesListFragment extends Fragment {
    private NoteSource source;
    private NotesAdapter adapter;
    private RecyclerView recyclerView;

    private SharedPreferences sharedPref = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        source = new PreferencesNoteSource(getActivity().getPreferences(Context.MODE_PRIVATE));

        adapter = new NotesAdapter(this, source);
        adapter.setClickListener((view1, position) -> {
            showNote(position);
        });
        recyclerView.setAdapter(adapter);
    }

    private void showNote(int position) {
        if (isLand()) {
            showNoteLand(position);
        } else {
            showNotePort(position);
        }
    }

    void showNoteLand(int position) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.noteDetailFragment_container, NoteDetailFragment.newInstance(position))
                .commit();
    }


    void showNotePort(int position) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.notesListFragment_container, NoteDetailFragment.newInstance(position))
                .commit();
    }

    private boolean isLand() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.notes_list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear:
                int size = source.size();
                source.clearNotes();
                adapter.notifyItemRangeRemoved(0, size);
                return true;
            case R.id.action_newNote:
                source.addNote(0, new Note("af", "dsa", ""));
                adapter.notifyItemInserted(0);
                recyclerView.scrollToPosition(0);
                return true;
        }
        return onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_delete:
                source.deleteNote(adapter.getMenuPosition());
                adapter.notifyItemRemoved(adapter.getMenuPosition());
                return true;
            case R.id.action_update:
                source.updateNote(adapter.getMenuPosition(), new Note("", "", ""));
                adapter.notifyItemChanged(adapter.getMenuPosition());
                return true;
        }
        return super.onContextItemSelected(item);
    }

}