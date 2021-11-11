package com.geekbrains.notes;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

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
        notes = new Notes[]{note_1, note_2, note_3, note_4};

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