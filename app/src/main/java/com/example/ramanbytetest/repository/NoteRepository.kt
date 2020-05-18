package com.example.ramanbytetest.repository

import android.content.Context
import android.os.AsyncTask
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.ramanbytetest.clickListener.SaveSuccessListener
import com.example.ramanbytetest.db.AppDatabase
import com.example.ramanbytetest.model.TaskModel


class NoteRepository(context: Context?,success: SaveSuccessListener) {
    private val DB_NAME = "user_db"
    private val noteDatabase: AppDatabase
    var data_save_listener:SaveSuccessListener?=null


    fun insertTask(
        first_name: String,
        last_name: String,
        phone_number:String,
        city:String,
        state:String,
        pin_code:String
    ) {

        var task = TaskModel(first_name = first_name,last_name = last_name,phone_number = phone_number,city = city,state = state,pin_code =pin_code);

        insertTask(task);
    }

    fun insertTask(note: TaskModel?) {


        object : AsyncTask<Void?, Void?, Void?>() {

            override fun doInBackground(vararg p0: Void?): Void? {

                noteDatabase.taskDao().insert(task = note)


                return null;
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)

                data_save_listener?.saveSuccess()

            }

        }.execute()
    }


    fun updateTask(id: Int,first_name: String,last_name: String,phone_number: String,city: String,state: String,pin_code: String) {
        object : AsyncTask<Void?, Void?, Void?>() {

            override fun doInBackground(vararg p0: Void?): Void? {
                noteDatabase.taskDao().updateItem(id,first_name,last_name,phone_number,city,state,pin_code)

                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)

                println("updateee")
                data_save_listener?.saveSuccess()

            }
        }.execute()


    }


    fun getTask(id: Int): LiveData<TaskModel?>? {
        return noteDatabase.taskDao().getTask(id)
    }



    val tasks: LiveData<List<TaskModel>>
        get() = noteDatabase.taskDao().getAllDetails()

    init {
        data_save_listener=success

        noteDatabase =
            Room.databaseBuilder(context!!, AppDatabase::class.java, DB_NAME).build()
    }
}