package com.example.ramanbytetest.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ramanbytetest.model.TaskModel


@Dao
interface TaskDao {

    @Query("SELECT * FROM taskmodel")
    fun getAllDetails(): LiveData<List<TaskModel>>

    @Query("SELECT * FROM taskmodel WHERE id =:taskId")
    fun getTask(taskId: Int): LiveData<TaskModel?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: TaskModel?)

    @Delete
    fun delete(task: TaskModel?)

    @Query("UPDATE TaskModel SET first_name = :firstName ,last_name=:lastName,phone_number=:phone_number,city=:city,state=:state,pin_code=:pin WHERE id = :id ")

    fun updateItem(id: Int, firstName: String,lastName:String,phone_number:String,city:String,state:String,pin:String)
}