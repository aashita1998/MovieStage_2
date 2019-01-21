package com.example.aashitachowdary.moviestage_2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    ArrayList<Review> list;
    Context context;
    public ReviewAdapter(Details details, ArrayList<Review> list) {
        this.context=details;
        this.list=list;
    }

    @NonNull
    @Override
    public ReviewAdapter.ReviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.review,viewGroup,false);
        return new ReviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewHolder reviewHolder, int i) {
         reviewHolder.author_text.setText(list.get(i).getAuthor());
         reviewHolder.content_text.setText(list.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ReviewHolder extends RecyclerView.ViewHolder {
        TextView author_text,content_text;
        public ReviewHolder(@NonNull View itemView) {

            super(itemView);
            author_text=itemView.findViewById(R.id.author);
            content_text=itemView.findViewById(R.id.content);
        }
    }
}
