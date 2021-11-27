package com.geekbrains.notes;

import android.content.SharedPreferences;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PreferencesNoteSource implements NoteSource {

    private final SharedPreferences sharedPref;
    private List<Note> notesList;
    private final String NOTE_DATA = "NOTE_DATA";

    public PreferencesNoteSource(SharedPreferences sharedPref) {
        this.sharedPref = sharedPref;
        fetch();
    }

    @Override
    public Note getNote(int position) {
        return notesList.get(position);
    }

    @Override
    public int size() {
        return notesList.size();
    }

    @Override
    public void deleteNote(int position) {
        notesList.remove(position);
        update();
    }

    @Override
    public void updateNote(int position, Note note) {
        notesList.set(position, note);
        update();
    }

    @Override
    public void addNote(int position, Note note) {
        notesList.add(0, note);
        update();
    }

    @Override
    public void clearNotes() {
        notesList.clear();
        update();
    }

    private void fetch() {
        String json = sharedPref.getString(NOTE_DATA, null);
        if (json == null) {
            notesList = new ArrayList<>();
        } else {
            Type type = new TypeToken<ArrayList<Note>>() {
            }.getType();
            notesList = new GsonBuilder().create().fromJson(json, type);
        }
    }

    private void update() {
        String json = new GsonBuilder().create().toJson(notesList);
        sharedPref.edit()
                .putString(NOTE_DATA, json)
                .apply();
    }

}
