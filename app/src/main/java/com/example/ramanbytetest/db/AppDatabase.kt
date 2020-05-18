package com.example.ramanbytetest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ramanbytetest.model.TaskModel
import kotlinx.coroutines.CoroutineScope

@Database(entities = [TaskModel::class],version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase(){

    abstract fun taskDao():TaskDao
}

