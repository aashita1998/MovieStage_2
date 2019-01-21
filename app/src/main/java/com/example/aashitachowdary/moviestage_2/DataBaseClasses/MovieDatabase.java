package com.example.aashitachowdary.moviestage_2.DataBaseClasses;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {FavMovieData.class},version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
    public  static volatile MovieDatabase INSTANCE;
    static MovieDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (MovieDatabase.class){
                if (INSTANCE==null){
                    INSTANCE=Room.databaseBuilder(context.getApplicationContext(),MovieDatabase.class,"word_database").fallbackToDestructiveMigration().build();


                }
            }

        }
        return INSTANCE;
    }
}
