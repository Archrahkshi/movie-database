package com.archrahkshi.moviedatabase.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.archrahkshi.moviedatabase.data.db.entities.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface MovieDao {
    @Insert(onConflict = REPLACE)
    fun insert(vararg movie: Movie): Completable

    @Delete
    fun delete(movie: Movie): Completable

    @Query("SELECT * FROM movie")
    fun getAll(): Observable<Array<Movie>>
}
