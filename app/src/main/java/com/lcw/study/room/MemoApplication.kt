package com.lcw.study.room

import android.app.Application
import com.lcw.study.room.data.database.MemoDatabase
import com.lcw.study.room.ui.main.MemoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MemoApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { MemoDatabase.getInstance(this, applicationScope) }
    val repository by lazy { MemoRepository(database.memoDao()) }
}
