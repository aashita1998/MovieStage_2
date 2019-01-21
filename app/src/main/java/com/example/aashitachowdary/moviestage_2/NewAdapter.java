package com.example.aashitachowdary.moviestage_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class NewAdapter extends RecyclerView.Adapter<NewAdapter.PopularHolder> {
    FragmentActivity activity;
    List<Film> filmList;
    public NewAdapter(FragmentActivity activity, ArrayList<Film> filmArrayList) {
        this.activity=activity;
        this.filmList=filmArrayList;
    }

    @NonNull
    @Override
    public NewAdapter.PopularHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mainscreen,viewGroup,false);
        return new PopularHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewAdapter.PopularHolder popularHolder, int i) {
    popularHolder.textView.setText(filmList.get(i).getTitle());
        Picasso.with(activity).load(filmList.get(i).getPoster()).into(popularHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public class PopularHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        public PopularHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
            textView=itemView.findViewById(R.id.tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           int position=getAdapterPosition();
           String image=filmList.get(position).getPoster();
           String  title=filmList.get(position).getTitle();
           String  overview=filmList.get(position).getOverview();
           String releasedate=filmList.get(position).getRelease_date();
           String id=filmList.get(position).getId();
           String vote=filmList.get(position).getVote();
            Bundle arguments=new Bundle();
            arguments.putString("image",image);
            arguments.putString("title",title);
            arguments.putString("overview",overview);
            arguments.putString("releasedate",releasedate);
            arguments.putString("vote",vote);
            arguments.putString("id",id);
            Intent i=new Intent(activity,Details.class);
            i.putExtras(arguments);
            activity.startActivity(i);

        }
    }
}
