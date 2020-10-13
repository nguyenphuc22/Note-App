package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class EditorActivity extends AppCompatActivity {
    EditText edtNote;
    int nodeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        edtNote = findViewById(R.id.edtNote);

        nodeId = this.getIntent().getIntExtra("noteId",-1);

        if (nodeId != -1) {
            edtNote.setText(MainActivity.mNotes.get(nodeId).toString());
        } else {

            MainActivity.mNotes.add("");
            nodeId = MainActivity.mNotes.size() - 1;
            MainActivity.mAdapterNote.notifyDataSetChanged();
        }

        edtNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.mNotes.set(nodeId, String.valueOf(s));
                MainActivity.mAdapterNote.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getSharedPreferences
                        (getPackageName(),MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(MainActivity.mNotes);

                sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}