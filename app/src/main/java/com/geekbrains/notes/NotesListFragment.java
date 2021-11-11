package com.geekbrains.notes;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NotesListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

    }

    private void initView(View view) {

        LinearLayout notesListLayout = view.findViewById(R.id.noteDetailFragment_container);

        for (int i = 0; i < MainActivity.notes.length; i++) {
            Notes note = MainActivity.notes[i];
            TextView noteTextView = new TextView(getContext());

            noteTextView.setText(note.getHeadline());
            noteTextView.setTextSize(getResources().getDimension(R.dimen.headLine_textSize));
            noteTextView.setTextColor(Color.BLACK);

            final int position = i;
            noteTextView.setOnClickListener(v -> {
                showNote(position);

            });
            notesListLayout.addView(noteTextView);
        }

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
              getChildFragmentManager()
                      .beginTransaction()
                      .addToBackStack(null)
                      .replace(R.id.noteDetailChildFragment_container, NoteDetailFragment.newInstance(position))
                      .commit();
    }

    private boolean isLand(){
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }


}