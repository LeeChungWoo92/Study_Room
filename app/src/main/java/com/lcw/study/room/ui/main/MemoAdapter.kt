package com.lcw.study.room.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lcw.study.room.R
import com.lcw.study.room.data.model.Memo
import com.lcw.study.room.databinding.ItemMemoBinding

class MemoAdapter : RecyclerView.Adapter<MemoAdapter.MainViewHolder>() {
    private lateinit var binding: ItemMemoBinding
    private val memoList: ArrayList<Memo> = ArrayList()
    var callbackListener: RecyclerViewCallBackListner? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_memo,
            parent,
            false
        )

        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(memoList[position])
    }

    override fun getItemCount(): Int = memoList.size

    fun setItem(item: List<Memo>) {
        memoList.clear()
        memoList.addAll(item)
        notifyDataSetChanged()

    }


    inner class MainViewHolder(private val binding: ItemMemoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(memo: Memo) {
            binding.data = memo
            binding.executePendingBindings()


            binding.checkFavorite.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {

                    callbackListener?.callBack(
                        memoList[adapterPosition].id,
                        true
                    )
                } else {
                    callbackListener?.callBack(
                        memoList[adapterPosition].id,
                        false
                    )
                }
            }
        }


    }


    interface RecyclerViewCallBackListner {
        fun callBack(id: Int, favorite: Boolean)
    }
}