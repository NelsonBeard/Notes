package com.geekbrains.notes;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static Notes[] notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Notes note_1 = new Notes("Заголовок заметки 1", "содержание заметки 1", "01.11.2021");
        Notes note_2 = new Notes("Заголовок заметки 2", "содержание заметки 2", "02.11.2021");
        Notes note_3 = new Notes("Заголовок заметки 3", "содержание заметки 3", "03.11.2021");
        Notes note_4 = new Notes("Заголовок заметки 4", "содержание заметки 4", "04.11.2021");
        notes = new Notes[]{note_1, note_2, note_3, note_4};

        findViewById(R.id.buttonSettings).setOnClickListener(v -> {

        });

        View notesListLayout = findViewById(R.id.mainMenuLayout);
        findViewById(R.id.buttonNotesList).setOnClickListener(v -> {

            notesListLayout.setVisibility(View.INVISIBLE);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.notesListFragment_container, new NotesListFragment())
                    .commit();
        });




    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getFragments().size() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}