package com.archrahkshi.moviedatabase.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.archrahkshi.moviedatabase.db.entities.Actor
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface ActorDao {
    @Insert(onConflict = REPLACE)
    fun insert(vararg actor: Actor): Completable

    @Delete
    fun delete(actor: Actor): Completable

    @Query("SELECT * FROM actor")
    fun getAll(): Observable<Array<Actor>>
}
