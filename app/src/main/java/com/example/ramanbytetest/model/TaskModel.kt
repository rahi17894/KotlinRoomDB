package com.example.ramanbytetest.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class TaskModel(
    @field:PrimaryKey(autoGenerate = true)
    @NonNull var id: Int?=null,
    @field:ColumnInfo(name = "first_name") var first_name: String,
    @field:ColumnInfo(name = "last_name") var last_name: String,
    @field:ColumnInfo(name = "phone_number") var phone_number: String,
    @field:ColumnInfo(name = "city") var city: String,
    @field:ColumnInfo(name = "state") var state: String,
    @field:ColumnInfo(name = "pin_code") var pin_code: String
)