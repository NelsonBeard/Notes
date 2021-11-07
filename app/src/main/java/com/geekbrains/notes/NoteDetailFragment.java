package com.geekbrains.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NoteDetailFragment extends Fragment {
    private static final String ARG_POSITION = "ARG_POSITION";
    private int position = -1;

    public NoteDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        buttonBackPressed(view);

    }



    private void buttonBackPressed(View view) {
        view.findViewById(R.id.buttonBack).setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }

    public static NoteDetailFragment newInstance(int position) {
        NoteDetailFragment noteDetail = new NoteDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        noteDetail.setArguments(args);
        return noteDetail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }
    }

    private void initView(View view) {
        Notes note = MainActivity.notes[position];
            TextView headlineTextView = view.findViewById(R.id.headline);
            headlineTextView.setText(note.getHeadline());

            TextView fullTextTextView = view.findViewById(R.id.fullText);
            fullTextTextView.setText(note.getFullText());

            TextView dateTextView = view.findViewById(R.id.date);
            dateTextView.setText(note.getDate());

    }
}