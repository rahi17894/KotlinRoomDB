package com.example.ramanbytetest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ramanbytetest.R
import com.example.ramanbytetest.model.TaskModel

class AdapterTaskList(val userList: ArrayList<TaskModel>) : RecyclerView.Adapter<AdapterTaskList.ViewHolder>() {



    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)

        return ViewHolder(v)

    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: TaskModel) {

             val tvFirstName = itemView.findViewById(R.id.tv_first_name) as TextView
            val tvLastName  = itemView.findViewById(R.id.tv_last_name) as TextView
            val tvSlNo  = itemView.findViewById(R.id.tv_sl_no) as TextView
            val tvPhoneNo  = itemView.findViewById(R.id.tv_phone_no) as TextView
            val tvCity  = itemView.findViewById(R.id.tv_city) as TextView
            val tvState  = itemView.findViewById(R.id.tv_state) as TextView
            val tvPinCode  = itemView.findViewById(R.id.tv_pin_code) as TextView



            tvFirstName.text = user.first_name
            tvLastName.text = user.last_name
            tvPhoneNo.text=user.phone_number
            tvSlNo.text=user.id.toString()
            tvCity.text=user.city
            tvState.text=user.state
            tvPinCode.text=user.pin_code




        }
    }
}