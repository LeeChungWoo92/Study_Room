package com.lcw.study.room.data.dao

import androidx.room.*
import com.lcw.study.room.data.model.Memo
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(memo:Memo)

    @Update
    fun update(memo:Memo)

    @Query("SELECT * FROM Memo ORDER BY id DESC")
    fun getAll() : Flow<List<Memo>>

    @Query("UPDATE Memo SET favorite = :value WHERE id = :idValue")
    suspend fun updateFavorite(idValue:Int,value: Boolean)
}
