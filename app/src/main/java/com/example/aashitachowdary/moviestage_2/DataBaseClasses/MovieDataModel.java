package com.example.aashitachowdary.moviestage_2.DataBaseClasses;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class MovieDataModel extends AndroidViewModel {
    MovieRepository movieRepository;
    private LiveData<List<FavMovieData>> listLiveData;

    public MovieDataModel(@NonNull Application application) {
        super(application);
        movieRepository=new MovieRepository(application);
        this.listLiveData=movieRepository.listLiveData();
    }
    public FavMovieData findMovieData(String id){
        FavMovieData favMovieData=movieRepository.findMovie(id);
        return  favMovieData;
    }
    public LiveData<List<FavMovieData>> getListLiveData(){
        return listLiveData;
    }
    public void insert(FavMovieData favMovieData){
        movieRepository.insertData(favMovieData);
    }
    public void deletedata(FavMovieData favMovieData){
        movieRepository.deleteData(favMovieData);
    }
}
