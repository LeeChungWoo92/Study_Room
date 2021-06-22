package com.lcw.study.room.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lcw.study.room.data.dao.MemoDao
import com.lcw.study.room.data.model.Memo

@Database(entities = [Memo::class], version = 1)
abstract class MemoDatabase: RoomDatabase() {
    abstract fun memoDao(): MemoDao
}