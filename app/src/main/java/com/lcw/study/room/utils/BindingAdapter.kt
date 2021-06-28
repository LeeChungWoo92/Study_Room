package com.lcw.study.room.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lcw.study.room.data.model.Memo
import com.lcw.study.room.ui.main.MemoAdapter

@BindingAdapter("memo", "favorite") // IPO 목록
fun RecyclerView.setMemoItem(memoItem: List<Memo>?, favoriteCheck: Boolean) {
    memoItem?.let { memoItemList ->
        if (favoriteCheck) {
            (adapter as MemoAdapter).setItem(
                memoItemList.filter {
                    it.favorite
                }
            )
        } else {
            (adapter as MemoAdapter).setItem(memoItemList)
        }

    }
}


