package com.archrahkshi.moviedatabase.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.archrahkshi.moviedatabase.db.entities.TvShow
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface TvShowDao {
    @Insert(onConflict = REPLACE)
    fun insert(vararg tvShow: TvShow): Completable

    @Delete
    fun delete(tvShow: TvShow): Completable

    @Query("SELECT * FROM tvshow")
    fun getAll(): Observable<Array<TvShow>>
}
