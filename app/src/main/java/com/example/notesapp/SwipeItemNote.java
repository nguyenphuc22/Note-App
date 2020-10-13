package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;

public class SwipeItemNote extends ItemTouchHelper.SimpleCallback {

    private AdapterNote mAdapterNote;

    // I want dragDirs and swipeDirs set default, so I will delete
    public SwipeItemNote(AdapterNote mAdapterNote) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mAdapterNote = mAdapterNote;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        this.mAdapterNote.deleteItem(position);

        HashSet<String> set = new HashSet<>(MainActivity.mNotes);

        MainActivity.sharedPreferences.edit().putStringSet("notes",set).apply();

    }
}
