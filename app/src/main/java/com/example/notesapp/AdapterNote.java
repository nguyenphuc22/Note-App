package com.example.notesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterNote extends RecyclerView.Adapter<AdapterNote.Viewholder> {

    ArrayList<String> mNotes;
    Context mContext;
    RecyclerItemClick mRecyclerItemClick;

    public AdapterNote(ArrayList<String> mNotes, Context mContext,RecyclerItemClick recyclerItemClick) {
        this.mNotes = mNotes;
        this.mContext = mContext;
        this.mRecyclerItemClick = recyclerItemClick;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.txtNote.setText(this.mNotes.get(position));
    }

    @Override
    public int getItemCount() {
        return this.mNotes.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView txtNote;
        public Viewholder(@NonNull final View itemView) {
            super(itemView);
            txtNote = itemView.findViewById(R.id.txtNote);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerItemClick.onClickItem(getAdapterPosition());
                }
            });

        }
    }

    public void deleteItem(int position) {
        this.mNotes.remove(position);
        this.notifyItemRemoved(position);
    }

}
