package com.lcw.study.room.data.dao

import androidx.room.*
import com.lcw.study.room.data.model.Memo

@Dao
interface MemoDao {
    @Insert
    fun insert(memo:Memo)

    @Update
    fun update(memo:Memo)

    @Delete
    fun delete(memo:Memo)

    @Query("SELECT * FROM Memo")
    fun getAll() : List<Memo>
}
