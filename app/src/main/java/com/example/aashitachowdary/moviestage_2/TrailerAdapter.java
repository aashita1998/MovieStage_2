package com.example.aashitachowdary.moviestage_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class TrailerAdapter  extends RecyclerView.Adapter<TrailerAdapter.TrailerHolder> {
    Context context;
    ArrayList<Trailer> trailerArrayList;
    public TrailerAdapter(Details details, ArrayList<Trailer> trailersList) {
        this.context=details;
        this.trailerArrayList=trailersList;
    }

    @NonNull
    @Override
    public TrailerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.trailer,viewGroup,false);
        return new TrailerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerHolder trailerHolder, int i) {
trailerHolder.textView.setText(trailerArrayList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return trailerArrayList.size();
    }

    public class TrailerHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public TrailerHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_trailer);
            textView=itemView.findViewById(R.id.text_tube);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION){
                        String key=trailerArrayList.get(position).getKey();
                        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=" + key));
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
