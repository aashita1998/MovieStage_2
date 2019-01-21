package com.example.aashitachowdary.moviestage_2.DataBaseClasses;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert
    void insertData(FavMovieData favMovieData);
    @Query("SELECT * FROM favDataTable")
    LiveData<List<FavMovieData>> getData();
    @Delete
    void deleteData(FavMovieData favMovieData);
    @Query("SELECT * FROM favDataTable WHERE id== :id")
    FavMovieData isMoviePresent(String id);
}
