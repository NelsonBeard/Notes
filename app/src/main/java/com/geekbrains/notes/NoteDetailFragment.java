package com.geekbrains.notes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NoteDetailFragment extends Fragment {
    private static final String ARG_POSITION = "ARG_POSITION";
    private int position = -1;

    private NoteSource source;
    private NotesAdapter adapter;

    private Note note;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
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
            requireActivity().getSupportFragmentManager().popBackStack();
            updateNote(view);
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

        source = new PreferencesNoteSource(getActivity().getPreferences(Context.MODE_PRIVATE));
        adapter = new NotesAdapter(this, source);
        note = source.getNote(position);

        TextView headlineTextView = view.findViewById(R.id.headline);
        headlineTextView.setText(note.getHeadline());

        TextView fullTextTextView = view.findViewById(R.id.fullText);
        fullTextTextView.setText(note.getFullText());

        TextView dateTextView = view.findViewById(R.id.date);
        dateTextView.setText(note.getDate());

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.note_detail_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                deleteNoteAlertDialog();
                return true;
        }
        return onOptionsItemSelected(item);
    }

    private void deleteNoteAlertDialog() {
        new AlertDialog.Builder(getActivity())
                .setCancelable(true)
                .setTitle("Удалить заметку?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requireActivity().getSupportFragmentManager().popBackStack();
                        source.deleteNote(position);
                        adapter.notifyItemRemoved(position);
                    }
                })
                .setNegativeButton("Нет", (dialog, which) -> dialog.cancel())
                .show();
    }

    private void updateNote(View view) {

        TextView headlineTextView = view.findViewById(R.id.headline);
        String headline = headlineTextView.getText().toString();

        TextView fullTextTextView = view.findViewById(R.id.fullText);
        String fullText = fullTextTextView.getText().toString();

        TextView dateTextView = view.findViewById(R.id.date);
        String date = dateTextView.getText().toString();

        source.updateNote(position, new Note(headline, fullText, date));
    }
}