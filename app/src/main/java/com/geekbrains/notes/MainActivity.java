package com.geekbrains.notes;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static Notes[] notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_info:
                        Toast.makeText(MainActivity.this, "Информация о приложении", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_settings:
                        Toast.makeText(MainActivity.this, "Настройки", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });

        Notes note_1 = new Notes("Заголовок заметки 1", "содержание заметки 1", "01.11.2021");
        Notes note_2 = new Notes("Заголовок заметки 2", "содержание заметки 2", "02.11.2021");
        Notes note_3 = new Notes("Заголовок заметки 3", "содержание заметки 3", "03.11.2021");
        Notes note_4 = new Notes("Заголовок заметки 4", "содержание заметки 4", "04.11.2021");
        Notes note_5 = new Notes("Заголовок заметки 5", "содержание заметки 5", "04.11.2021");
        Notes note_6 = new Notes("Заголовок заметки 6", "содержание заметки 6", "04.11.2021");
        Notes note_7 = new Notes("Заголовок заметки 7", "содержание заметки 7", "04.11.2021");
        Notes note_8 = new Notes("Заголовок заметки 8", "содержание заметки 8", "04.11.2021");
        Notes note_9 = new Notes("Заголовок заметки 9", "содержание заметки 9", "01.11.2021");
        Notes note_10 = new Notes("Заголовок заметки 10", "содержание заметки 10", "02.11.2021");
        Notes note_11 = new Notes("Заголовок заметки 11", "содержание заметки 11", "03.11.2021");
        Notes note_12 = new Notes("Заголовок заметки 12", "содержание заметки 12", "04.11.2021");
        Notes note_13 = new Notes("Заголовок заметки 13", "содержание заметки 13", "04.11.2021");
        Notes note_14 = new Notes("Заголовок заметки 14", "содержание заметки 14", "04.11.2021");
        Notes note_15 = new Notes("Заголовок заметки 15", "содержание заметки 15", "04.11.2021");
        Notes note_16 = new Notes("Заголовок заметки 16", "содержание заметки 16", "04.11.2021");
        Notes note_17 = new Notes("Заголовок заметки 17", "содержание заметки 17", "04.11.2021");

        notes = new Notes[]{note_1, note_2, note_3, note_4, note_5, note_6, note_7, note_8, note_9,
                note_10, note_11, note_12, note_13, note_14, note_15, note_16, note_17};

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("")
                .replace(R.id.notesListFragment_container, new NotesListFragment())
                .commit();
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