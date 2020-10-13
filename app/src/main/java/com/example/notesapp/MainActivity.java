package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity implements RecyclerItemClick{
    // First, Create RecyclerView
    RecyclerView mRecyclerView;
    static AdapterNote mAdapterNote;
    static ArrayList<String> mNotes;
    // Store Array Notes
    static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);

        sharedPreferences = getSharedPreferences(getPackageName(),MODE_PRIVATE);
        // Getting Array Notes

        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes",null);

        if (set != null) {
            mNotes = new ArrayList<>(set);
        } else {
            mNotes = new ArrayList<>();
            mNotes.add("Hello! I'm Phuc VR");
            mNotes.add("I'm 20");
        }

        mAdapterNote = new AdapterNote(mNotes,this,this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager
                (this,LinearLayoutManager.VERTICAL,false);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapterNote);
        // Next, add function click item

        // Create function delete item
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeItemNote(mAdapterNote));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onClickItem(int position) {
        Intent intent = new Intent(this,EditorActivity.class);
        intent.putExtra("noteId",position);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item_note,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.ic_Create: {
                Intent intent = new Intent(this,EditorActivity.class);
                startActivity(intent);
            }
        }

        return super.onOptionsItemSelected(item);
    }
}