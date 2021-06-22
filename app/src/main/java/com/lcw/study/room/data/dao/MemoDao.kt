package com.lcw.study.room.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.lcw.study.room.data.model.Memo

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
