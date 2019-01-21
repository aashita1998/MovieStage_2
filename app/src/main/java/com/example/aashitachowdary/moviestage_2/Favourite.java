package com.example.aashitachowdary.moviestage_2;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aashitachowdary.moviestage_2.DataBaseClasses.FavMovieData;
import com.example.aashitachowdary.moviestage_2.DataBaseClasses.MovieDataModel;

import java.util.ArrayList;
import java.util.List;

public class Favourite extends Fragment {
    public static final String TAG="FavouriteFragment";
    StateRec recyclerView;
    ProgressDialog dialog;
    MovieDataModel movieDataModel;
    List<FavMovieData> favMovieDataList;
    ViewModelProvider.AndroidViewModelFactory viewModelFactory;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.favourite,container,false);
        recyclerView=view.findViewById(R.id.favrec);
        movieDataModel=ViewModelProviders.of(getActivity(),viewModelFactory).get(MovieDataModel.class);
        favMovieDataList=new ArrayList<>();
        dialog=new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();
        LoadFavData();
        return view;
    }
    public void LoadFavData(){
        movieDataModel.getListLiveData().observe(this, new Observer<List<FavMovieData>>() {
            @Override
            public void onChanged(@Nullable List<FavMovieData> favMovieData) {
                favMovieDataList=favMovieData;
                FavouriteAdapter favouriteAdapter=new FavouriteAdapter(getActivity(),favMovieDataList);
                if(favMovieDataList.size()==0){
                    Toast.makeText(getActivity(), "Favrouties is empty", Toast.LENGTH_SHORT).show();
                }
                if(getActivity().getApplication().getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT){
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                }
                else{
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
                }
                recyclerView.setAdapter(favouriteAdapter);
            }
        });
        dialog.dismiss();
    }


}
