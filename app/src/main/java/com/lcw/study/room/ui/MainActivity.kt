package com.lcw.study.room.ui

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.lcw.study.room.R
import com.lcw.study.room.data.database.MemoDatabase
import com.lcw.study.room.data.model.Memo
import com.lcw.study.room.utils.DateTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val btnSave: Button by lazy { findViewById(R.id.btn_save) }
    private val inputMemo: TextInputEditText by lazy { findViewById(R.id.edit_id) }
    private val db by lazy { MemoDatabase.getInstance(context = applicationContext) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.memoDao().insert(
                    Memo(
                        content = inputMemo.text.toString(),
                        createDate = DateTime.dateNow(),
                        favorite = false
                    )
                )
            }

            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_LONG).show()
        }
    }
}