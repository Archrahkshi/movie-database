package com.archrahkshi.moviedatabase.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.archrahkshi.moviedatabase.data.db.entities.Movie
import io.reactivex.rxjava3.core.Single

@Dao
interface MovieDao {
    @Insert(onConflict = IGNORE)
    suspend fun insert(vararg movie: Movie)

    @Update
    suspend fun updateLike(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id = :id)")
    suspend fun isMovieSaved(id: Int): Boolean

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovie(id: Int): Single<Movie>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavoriteMovies(): Single<List<Movie>>
}
