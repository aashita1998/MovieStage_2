package com.example.aashitachowdary.moviestage_2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aashitachowdary.moviestage_2.DataBaseClasses.FavMovieData;
import com.example.aashitachowdary.moviestage_2.DataBaseClasses.MovieDataModel;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Details extends AppCompatActivity {
ImageView imageView;
TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7;
String title;
String poster;
String vote;
String release_date;
String overview;
String id;
RecyclerView recyclerView1,recyclerView2;
ArrayList<Review> list;
ArrayList<Trailer> trailersList;
RequestQueue requestQueue;
LikeButton favoriteButton;
List<FavMovieData> favMovieDataList;
MovieDataModel movieDataModel;
    ViewModelProvider.AndroidViewModelFactory viewModelFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageView=findViewById(R.id.poster);
        textView1=findViewById(R.id.tv1);
        textView2=findViewById(R.id.tv2);
        textView3=findViewById(R.id.tv3);
        textView4=findViewById(R.id.tv4);
        textView5=findViewById(R.id.tv5);
        textView6=findViewById(R.id.tv6);
        textView7=findViewById(R.id.review);
        recyclerView1=findViewById(R.id.reviewrec);
        recyclerView2=findViewById(R.id.trailrec);
        movieDataModel=ViewModelProviders.of(this,viewModelFactory).get(MovieDataModel.class);
        requestQueue=Volley.newRequestQueue(this);
        favoriteButton=findViewById(R.id.fav);
        list=new ArrayList<>();
        trailersList=new ArrayList<>();
        favMovieDataList=new ArrayList<>();
        Bundle arguments=getIntent().getExtras();
        poster=arguments.getString("image");
        vote=arguments.getString("vote");
        overview=arguments.getString("overview");
        release_date=arguments.getString("releasedate");
        title=arguments.getString("title");
        id=arguments.getString("id");
        Picasso.with(this).load(poster).into(imageView);
        textView1.setText(title);
        textView3.setText(vote);
        textView2.setText(release_date);
        textView4.setText(overview);
        addFav();
        Reviews();
        Trailer();
        searchforMovie();
    }
    public void searchforMovie(){


       movieDataModel.getListLiveData().observe(this, new Observer<List<FavMovieData>>() {
           @Override
           public void onChanged(@Nullable List<FavMovieData> favMovieData) {
               for (int i=0;i<favMovieData.size();i++)
               {

                   String d=favMovieData.get(i).getId();
                   if (d.equalsIgnoreCase(id))
                   {
                       favoriteButton.setLiked(true);

                   }

               }
           }
       });

    }
    public void addFav(){
        favoriteButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {


                saveData();
                Toast.makeText(Details.this, "Added To Favorites", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void unLiked(LikeButton likeButton) {

                deletedata();
                Toast.makeText(Details.this, "Removed From Favorites", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public  void saveData(){
        FavMovieData favMovieData=new FavMovieData();
        favMovieData.setMovieTitle(title);
        favMovieData.setMoviePoster(poster);
        favMovieData.setMovieReleaseDate(release_date);
        favMovieData.setMovieVote(vote);
        favMovieData.setId(id);
        favMovieData.setMovieDec(overview);
        movieDataModel.insert(favMovieData);

    }
    public void deletedata(){
        FavMovieData favMovieData=new FavMovieData();
        favMovieData.setMovieTitle(title);
        favMovieData.setMovieDec(overview);
        favMovieData.setMovieVote(vote);
        favMovieData.setMovieReleaseDate(release_date);
        favMovieData.setMoviePoster(poster);
        favMovieData.setId(id);
        movieDataModel.deletedata(favMovieData);
    }
    public void Reviews(){
        String id=getIntent().getStringExtra("id");
        final String review="https://api.themoviedb.org/3/movie/ " + id + "/reviews?api_key=27d528e134cc1433e2743043833772ac";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, review, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String author = null;
                String content = null;
                try {
                    JSONObject root = new JSONObject(response);
                    JSONArray result = root.getJSONArray("results");
                    if (result.length()==0) {
                        textView7.setText(R.string.no_data);
                    } else {
                        for (int i = 0; i < result.length(); i++) {
                            JSONObject object = result.getJSONObject(i);
                            author = object.getString("author");
                            content = object.getString("content");
                            Review rev = new Review(author, content);
                            list.add(rev);
                            textView5.setText(author);
                            textView6.setText(content);
                            ReviewAdapter reviewAdapter = new ReviewAdapter(Details.this, list);

                            recyclerView1.setLayoutManager(new LinearLayoutManager(Details.this));
                            recyclerView1.setAdapter(reviewAdapter);

                        }
                    }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
    public void Trailer(){
        String id=getIntent().getStringExtra("id");
        String trailer="https://api.themoviedb.org/3/movie/" + id + "/videos?api_key=27d528e134cc1433e2743043833772ac";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, trailer, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
             String id=null;
             String key=null;
             String name=null;
             String type=null;
                try {
                    JSONObject root=new JSONObject(response);
                    JSONArray result=root.getJSONArray("results");
                    for (int i = 0; i <result.length() ; i++) {
                        JSONObject object=result.getJSONObject(i);
                        id=object.getString("id");
                        key=object.getString("key");
                        name=object.getString("name");
                        type=object.getString("type");
                        Trailer trailer1=new Trailer(id,key,name,type);
                        trailersList.add(trailer1);
                    }
                    TrailerAdapter trailerAdapter=new TrailerAdapter(Details.this,trailersList);
                    recyclerView2.setLayoutManager(new LinearLayoutManager(Details.this));
                    recyclerView2.setAdapter(trailerAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}
