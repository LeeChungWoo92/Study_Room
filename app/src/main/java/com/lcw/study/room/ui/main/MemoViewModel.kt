package com.lcw.study.room.ui.main

import androidx.lifecycle.*
import com.lcw.study.room.data.model.Memo
import kotlinx.coroutines.launch

class MemoViewModel(private val repository: MemoRepository) : ViewModel() {

    var memoList =  MutableLiveData<List<Memo>>()
    var favoriteListCheck = MutableLiveData<Boolean>()

    val getAllMemo: LiveData<List<Memo>> = repository.getAllMemo.asLiveData()


    fun insert(memo: Memo) = viewModelScope.launch {
        repository.insert(memo)
    }

    fun updateFavorite(id:Int,favorite:Boolean) = viewModelScope.launch {
        repository.updateFavorite(id,favorite)
    }
}


class MemoViewModelFactory(private val repository: MemoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MemoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
