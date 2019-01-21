package com.example.aashitachowdary.moviestage_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aashitachowdary.moviestage_2.DataBaseClasses.FavMovieData;
import com.squareup.picasso.Picasso;

import java.util.List;

class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteHolder> {
    List<FavMovieData> favMovieDataList;
    Context context;
    public FavouriteAdapter(FragmentActivity activity, List<FavMovieData> favMovieDataList) {
        this.context=activity;
        this.favMovieDataList=favMovieDataList;
    }

    @NonNull
    @Override
    public FavouriteAdapter.FavouriteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.favitem,viewGroup,false);
        return new FavouriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.FavouriteHolder favouriteHolder, int i) {
        Picasso.with(context).load(favMovieDataList.get(i).getMoviePoster()).placeholder(R.mipmap.ic_launcher).into(favouriteHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return favMovieDataList.size();
    }

    public class FavouriteHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public FavouriteHolder(@NonNull final View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.favimg);
            textView=itemView.findViewById(R.id.favtext);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    String image=favMovieDataList.get(position).getMoviePoster();
                    String title=favMovieDataList.get(position).getMovieTitle();
                    String overview=favMovieDataList.get(position).getMovieDec();
                    String releasedate=favMovieDataList.get(position).getMovieReleaseDate();
                    String id=favMovieDataList.get(position).getId();
                    String vote=favMovieDataList.get(position).getMovieVote();
                    Bundle bundle=new Bundle();
                    bundle.putString("image",image);
                    bundle.putString("title",title);
                    bundle.putString("overview",overview);
                    bundle.putString("releasedate",releasedate);
                    bundle.putString("id",id);
                    bundle.putString("vote",vote);
                    Intent i=new Intent(context,Details.class);
                    i.putExtras(bundle);
                    context.startActivity(i);
                }
            });
        }
    }
}
