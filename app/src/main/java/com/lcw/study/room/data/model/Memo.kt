package com.lcw.study.room.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memo(
    var content: String,
    var createDate: String,
    var favorite: Boolean
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
