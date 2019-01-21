package com.example.aashitachowdary.moviestage_2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Popular extends Fragment {
    public static final String TAG = "PopularFragment";
    StateRec recyclerView;
    ArrayList<Film> filmArrayList;
    RequestQueue requestQueue;
    String imagelink = "https://image.tmdb.org/t/p/w500";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popular, container, false);
        recyclerView = view.findViewById(R.id.popular_rec);
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        }
        requestQueue = Volley.newRequestQueue(getActivity());
        movie();
        checkConnectivity();
        return view;
    }

    public void movie() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=27d528e134cc1433e2743043833772ac";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                filmArrayList = new ArrayList<>();
                String title = null;
                String poster = null;
                String vote = null;
                String overview = null;
                String releasedate = null;
                String id = null;

                try {
                    JSONObject root = new JSONObject(response);
                    JSONArray result = root.getJSONArray("results");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject object = result.getJSONObject(i);
                        title = object.getString("title");
                        id = object.getString("id");
                        poster = imagelink + object.getString("poster_path");
                        vote = object.getString("vote_average");
                        overview = object.getString("overview");
                        releasedate = object.getString("release_date");
                        Film film = new Film(title, poster, vote, overview, releasedate, id);
                        filmArrayList.add(film);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                NewAdapter adapter = new NewAdapter(getActivity(), filmArrayList);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    public void checkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            movie();
        } else {
            if (getActivity().getApplication().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT || getActivity().getApplication().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setMessage("No Internet Connection...Please Check Your Network");
                dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                });
                dialog.setCancelable(false);
                dialog.show();
            }

        }

    }
}
