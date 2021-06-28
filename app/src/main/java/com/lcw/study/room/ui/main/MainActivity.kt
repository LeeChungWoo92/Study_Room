package com.lcw.study.room.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import com.lcw.study.room.MemoApplication
import com.lcw.study.room.R
import com.lcw.study.room.data.model.Memo
import com.lcw.study.room.databinding.ActivityMainBinding
import com.lcw.study.room.utils.DateTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : BaseActivity(), MemoAdapter.RecyclerViewCallBackListner {
    private lateinit var binding: ActivityMainBinding
    private val adapter = MemoAdapter()
    private val memoViewModel: MemoViewModel by viewModels {
        MemoViewModelFactory((application as MemoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        binding.vm = memoViewModel
        binding.lifecycleOwner = this

        binding.rvMemoList.adapter = adapter
        adapter.callbackListener = this

        //전체메모리스트 옵저빙
        memoViewModel.getAllMemo.observe(this) {
            if (it.isEmpty()) {
                binding.emptyView.visibility = View.VISIBLE
                binding.rvMemoList.visibility = View.GONE
            } else {
                binding.emptyView.visibility = View.GONE
                binding.rvMemoList.visibility = View.VISIBLE
                memoViewModel.memoList.value = it
            }
        }

        binding.apply {
            btnSave.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                        memoViewModel.insert(
                            Memo(
                                content = editId.text.toString(),
                                createDate = DateTime.dateNow(),
                                favorite = false
                            )
                        )
                    }
                }
                showToast("저장되었습니다.")
                editId.clearFocus()
                editId.text?.clear()

            }


            // 관심메모만 보기 체크박스 클릭
            checkFavorite.setOnCheckedChangeListener { _, isChecked ->
                memoViewModel.favoriteListCheck.value = isChecked
            }

        }

    }

    // 메모리스트에서 관심체크박스 클릭 콜백
    override fun callBack(id: Int, favorite: Boolean) {
        Log.d("roomselect", "callBack : $id , $favorite")
        memoViewModel.updateFavorite(id, favorite)
    }

}