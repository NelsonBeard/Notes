package com.geekbrains.notes;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class NotesListFragment extends Fragment {

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

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        NotesAdapter adapter = new NotesAdapter(Arrays.asList(MainActivity.notes));
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
            case R.id.action_search:
                Toast.makeText(getActivity(), "Поиск", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_sort:
                Toast.makeText(getActivity(), "Сортировка заметок", Toast.LENGTH_SHORT).show();
                return true;
        }
        return onOptionsItemSelected(item);
    }

}