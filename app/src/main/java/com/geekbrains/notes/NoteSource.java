package com.geekbrains.notes;

public interface NoteSource {
    Note getNote(int position);

    int size();

    void deleteNote(int position);

    void updateNote(int position, Note note);

    void addNote(int position, Note note);

    void clearNotes();
}
