package com.example.jdr006.beautifulbulldog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.RealmResults;

public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.VoteViewHolder> {
    private Context context;
    private RealmResults<Vote> votes;

    public VoteAdapter(Context context, RealmResults<Vote> dataSet) {
        this.context = context;
        this.votes = dataSet;
    }

    public static class VoteViewHolder extends RecyclerView.ViewHolder {
        public TextView dogNameView, voterNameView, ratingView;
        public ImageView imageView;

        public VoteViewHolder(View v) {
            super(v);
            dogNameView = v.findViewById(R.id.name_view3);
            voterNameView = v.findViewById(R.id.voter_name);
            ratingView = v.findViewById(R.id.rating_view);
            imageView = v.findViewById(R.id.imageView2);
        }
    }

    @Override
    public int getItemCount() {
        return votes.size();
    }

    @Override
    public VoteAdapter.VoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.vote_cell, parent, false);
        VoteViewHolder vh = new VoteViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(VoteViewHolder holder, int position) {
        holder.dogNameView.setText(votes.get(position).getBulldog().getName());
        holder.voterNameView.setText(votes.get(position).getOwner().getUsername());
        holder.ratingView.setText(String.valueOf(votes.get(position).getRating()));

        if(votes.get(position).getBulldog().getImage() != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(votes.get(position).getBulldog().getImage(), 0, votes.get(position).getBulldog().getImage().length);
            holder.imageView.setImageBitmap(bmp);
        }
    }
}