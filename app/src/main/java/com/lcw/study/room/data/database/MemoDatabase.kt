package com.lcw.study.room.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lcw.study.room.data.dao.MemoDao
import com.lcw.study.room.data.model.Memo
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Memo::class], version = 1)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao


    companion object {
        private var instance: MemoDatabase? = null


        @Synchronized
        fun getInstance(context: Context, scope: CoroutineScope): MemoDatabase {
            if (instance == null) {
                synchronized(MemoDatabase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MemoDatabase::class.java,
                        "memo_database"

                    ).build()
                }

            }
            return instance!!

        }
    }
}

