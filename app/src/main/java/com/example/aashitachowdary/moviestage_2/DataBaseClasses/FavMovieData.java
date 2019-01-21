package com.example.aashitachowdary.moviestage_2.DataBaseClasses;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "favDataTable")
public class FavMovieData {
    @PrimaryKey
    @NonNull
    public String id;
    public String movieTitle;
    public String movieDec;
    public String moviePoster;
    public String movieReleaseDate;
    public String movieVote;

    @NonNull
    public String getId() {
        return String.valueOf(id);
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieDec() {
        return movieDec;
    }

    public void setMovieDec(String movieDec) {
        this.movieDec = movieDec;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieVote() {
        return movieVote;
    }

    public void setMovieVote(String movieVote) {
        this.movieVote = movieVote;
    }
}
