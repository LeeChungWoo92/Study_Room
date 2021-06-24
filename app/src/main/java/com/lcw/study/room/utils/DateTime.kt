package com.lcw.study.room.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object DateTime {

    /** 현재날짜 구하기 **/
    fun dateNow() : String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
             val localDate = LocalDate.now()
             localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
         } else {
             val now: Long = System.currentTimeMillis()
             val mDate = Date(now)
             val simpleDate = SimpleDateFormat("yyyy-MM-dd")
             simpleDate.format(mDate)
         }
        ////
    }

}