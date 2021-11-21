package com.geekbrains.notes;

import android.content.Context;

import androidx.annotation.NonNull;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoteSourceImp implements NoteSource {

    public static List<Note> notes;

    public NoteSourceImp(@NonNull Context context) {
        this.notes = new ArrayList<>(Arrays.asList(
                new Note("Заголовок заметки 1", "содержание заметки 1", "01.11.2021"),
                new Note("Заголовок заметки 2", "содержание заметки 2", "02.11.2021"),
                new Note("Заголовок заметки 3", "содержание заметки 3", "03.11.2021"),
                new Note("Заголовок заметки 4", "содержание заметки 4", "04.11.2021"),
                new Note("Заголовок заметки 5", "содержание заметки 5", "04.11.2021"),
                new Note("Заголовок заметки 6", "содержание заметки 6", "04.11.2021"),
                new Note("Заголовок заметки 7", "содержание заметки 7", "04.11.2021"),
                new Note("Заголовок заметки 8", "содержание заметки 8", "04.11.2021"),
                new Note("Заголовок заметки 9", "содержание заметки 9", "01.11.2021"),
                new Note("Заголовок заметки 10", "содержание заметки 10", "02.11.2021"),
                new Note("Заголовок заметки 11", "содержание заметки 11", "03.11.2021"),
                new Note("Заголовок заметки 12", "содержание заметки 12", "04.11.2021"),
                new Note("Заголовок заметки 13", "содержание заметки 13", "04.11.2021"),
                new Note("Заголовок заметки 14", "содержание заметки 14", "04.11.2021"),
                new Note("Заголовок заметки 15", "содержание заметки 15", "04.11.2021"),
                new Note("Заголовок заметки 16", "содержание заметки 16", "04.11.2021"),
                new Note("Заголовок заметки 17", "содержание заметки 17", "04.11.2021")
        ));
    }

    @Override
    public Note getNote(int position) {
        return notes.get(position);
    }

    @Override
    public int size() {
        return notes.size();
    }

    @Override
    public void deleteNote(int position) {
        notes.remove(position);
    }

    @Override
    public void updateNote(int position, Note note) {
        notes.set(position, note);
    }

    @Override
    public void addNote(Note note) {
        notes.add(note);
    }

    @Override
    public void clearNotes() {
        notes.clear();
    }
}
