package com.geekbrains.notes;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

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
                        Toast.makeText(MainActivity.this, R.string.app_info_toast, Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_settings:
                        Toast.makeText(MainActivity.this, R.string.settings_toast, Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });

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