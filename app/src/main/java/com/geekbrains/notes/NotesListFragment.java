package com.geekbrains.notes;

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
        Notes note_1 = new Notes("Заголовок заметки 1", "содержание заметки 1", "01.11.2021");
        Notes note_2 = new Notes("Заголовок заметки 2", "содержание заметки 2", "02.11.2021");
        Notes note_3 = new Notes("Заголовок заметки 3", "содержание заметки 3", "03.11.2021");
        Notes note_4 = new Notes("Заголовок заметки 4", "содержание заметки 4", "04.11.2021");

        LinearLayout notesListLayout = (LinearLayout) view;
        Notes[] notes = {note_1, note_2, note_3, note_4};

        for (Notes note : notes) {
            TextView noteTextView = new TextView(getContext());
            noteTextView.setText(note.getHeadline());
            noteTextView.setTextSize(30);
            noteTextView.setTextColor(Color.BLACK);
            notesListLayout.addView(noteTextView);
        }

    }
}