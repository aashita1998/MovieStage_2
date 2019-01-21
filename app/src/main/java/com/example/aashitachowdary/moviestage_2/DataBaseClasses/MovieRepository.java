package com.example.aashitachowdary.moviestage_2.DataBaseClasses;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class MovieRepository {
    private MovieDao movieDao;
    private LiveData<List<FavMovieData>> favMovieData;

    public MovieRepository(Application application) {
        MovieDatabase database=MovieDatabase.getDatabase(application);
        movieDao=database.movieDao();
        favMovieData=movieDao.getData();
    }
    public FavMovieData findMovie(String id){
        FavMovieData movieData=movieDao.isMoviePresent(id);
        return movieData;
    }
    public LiveData<List<FavMovieData>> listLiveData(){
        return favMovieData;
    }
    public void insertData(FavMovieData favMovieData){
        new MyTask(movieDao).execute(favMovieData);
    }
    public  void deleteData(FavMovieData favMovieData){
        new MyDeleteTask(movieDao).execute(favMovieData);
    }

    public class MyTask extends AsyncTask<FavMovieData,Void,Void> {
        public MovieDao dao;

        public MyTask(MovieDao movieDao) {
            dao=movieDao;
        }

        @Override
        protected Void doInBackground(FavMovieData... favMovieData) {
            dao.insertData(favMovieData[0]);
            return null;
        }
    }

    public  class MyDeleteTask extends AsyncTask<FavMovieData,Void,Void> {
        public MovieDao mdao;
        public MyDeleteTask(MovieDao movieDao) {
            mdao=movieDao;
        }

        @Override
        protected Void doInBackground(FavMovieData... favMovieData) {
            mdao.deleteData(favMovieData[0]);
            return null;
        }
    }
}
