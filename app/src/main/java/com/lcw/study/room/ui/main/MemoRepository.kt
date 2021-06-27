package com.lcw.study.room.ui.main

import androidx.annotation.WorkerThread
import com.lcw.study.room.data.dao.MemoDao
import com.lcw.study.room.data.model.Memo
import kotlinx.coroutines.flow.Flow

class MemoRepository(private val memoDao: MemoDao) {

    val getAllMemo: Flow<List<Memo>> = memoDao.getAll()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(memo: Memo) {
        memoDao.insert(memo)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateFavorite(id:Int,favorite: Boolean) {
        memoDao.updateFavorite(id,favorite)
    }
}