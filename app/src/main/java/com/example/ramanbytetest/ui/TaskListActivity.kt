package com.example.ramanbytetest.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ramanbytetest.R
import com.example.ramanbytetest.adapter.AdapterTaskList
import com.example.ramanbytetest.adapter.AdapterTaskListEditImage
import com.example.ramanbytetest.clickListener.EditClickListener
import com.example.ramanbytetest.clickListener.SaveSuccessListener
import com.example.ramanbytetest.databinding.ActivityMainBinding
import com.example.ramanbytetest.model.TaskModel
import com.example.ramanbytetest.repository.NoteRepository


class TaskListActivity : AppCompatActivity(), EditClickListener, SaveSuccessListener {

    private var doubleBackToExitPressedOnce: Boolean=false
    private var users = ArrayList<TaskModel>()
    private var adapter: AdapterTaskList? = null
    private var adapterImage: AdapterTaskListEditImage? = null
    private val REQUEST_CODE: Int = 121
    private var activityMainBinding: ActivityMainBinding? = null
    private var context: Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        setAdapter()
        clickListener()
        fetchDataFromDatabase()


    }

    private fun clickListener() {

        activityMainBinding?.ivFab?.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            intent.putExtra("edit", false)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    private fun setAdapter() {


        val mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        activityMainBinding?.rvUserDetails!!.setLayoutManager(mLayoutManager)

        val mLayoutManagers = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        activityMainBinding?.rvImage!!.setLayoutManager(mLayoutManagers)

        adapter = AdapterTaskList(users)
        adapterImage = AdapterTaskListEditImage(users, this)

        activityMainBinding?.rvUserDetails!!.adapter = adapter
        activityMainBinding?.rvImage!!.adapter = adapterImage


        val scrollListener: RecyclerView.OnScrollListener =
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (recyclerView === activityMainBinding?.rvUserDetails) {
                        activityMainBinding?.rvImage!!.removeOnScrollListener(this)
                        activityMainBinding?.rvImage!!.scrollBy(0, dy)
                        activityMainBinding?.rvImage!!.addOnScrollListener(this)
                    } else if (recyclerView === activityMainBinding?.rvImage) {
                        activityMainBinding?.rvUserDetails!!.removeOnScrollListener(this)
                        activityMainBinding?.rvUserDetails!!.scrollBy(0, dy)
                        activityMainBinding?.rvUserDetails!!.addOnScrollListener(this)
                    }
                }

                override fun onScrollStateChanged(
                    recyclerView: RecyclerView,
                    newState: Int
                ) {
                    super.onScrollStateChanged(recyclerView, newState)
                }
            }

        activityMainBinding?.rvUserDetails!!.addOnScrollListener(scrollListener)
        activityMainBinding?.rvImage!!.addOnScrollListener(scrollListener)


    }

    override fun editClickListener(position: Int) {

        val intent = Intent(this, FormActivity::class.java)
        intent.putExtra("id", users.get(position).id)
        intent.putExtra("first_name", users.get(position).first_name)
        intent.putExtra("last_name", users.get(position).last_name)
        intent.putExtra("phone", users.get(position).phone_number)
        intent.putExtra("city", users.get(position).city)
        intent.putExtra("state", users.get(position).state)
        intent.putExtra("pin", users.get(position).pin_code)
        intent.putExtra("edit", true)
        startActivityForResult(intent, REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {

            if (resultCode == Activity.RESULT_OK) {

                fetchDataFromDatabase()
            }
        }
    }


    private fun fetchDataFromDatabase() {
        val noteRepository = NoteRepository(applicationContext, this)


        users.clear()
        noteRepository.tasks.observe(
            this,
            Observer<List<TaskModel?>?> { notes ->
                for (note in notes!!) {
                    val task = TaskModel(
                        id = note?.id,
                        first_name = note!!.first_name,
                        last_name = note.last_name,
                        phone_number = note.phone_number,
                        city = note.city,
                        state = note.state,
                        pin_code = note.pin_code
                    )

                    users.add(task)

                }

                if (users.size > 0) {
                    activityMainBinding?.rvUserDetails?.visibility = View.VISIBLE
                    activityMainBinding?.tvNoDataFound?.visibility = View.GONE
                } else {
                    activityMainBinding?.rvUserDetails?.visibility = View.GONE
                    activityMainBinding?.tvNoDataFound?.visibility = View.VISIBLE
                }
                adapter!!.notifyDataSetChanged()


            })
    }

    override fun saveSuccess() {
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish()
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

}
