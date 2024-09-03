package com.archrahkshi.moviedatabase.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.archrahkshi.moviedatabase.data.db.dao.ActorDao
import com.archrahkshi.moviedatabase.data.db.dao.MovieDao
import com.archrahkshi.moviedatabase.data.db.dao.TvShowDao
import com.archrahkshi.moviedatabase.data.db.entities.Actor
import com.archrahkshi.moviedatabase.data.db.entities.Movie
import com.archrahkshi.moviedatabase.data.db.entities.TvShow

@Database(entities = [Actor::class, Movie::class, TvShow::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun actorDao(): ActorDao
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    AppDatabase::class.simpleName
                ).build()
            }
            return instance!!
        }
    }
}
