package com.example.ramanbytetest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ramanbytetest.R
import com.example.ramanbytetest.clickListener.EditClickListener
import com.example.ramanbytetest.model.TaskModel

class AdapterTaskListEditImage(val userList: ArrayList<TaskModel>,  clickListener: EditClickListener) : RecyclerView.Adapter<AdapterTaskListEditImage.ViewHolder>() {



        var mClickListener: EditClickListener=clickListener


    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_image, parent, false)

        return ViewHolder(v)

    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cl_edit_button.setOnClickListener {
            println("click here")



            try {

                println("click here")

                mClickListener.editClickListener(position)

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }


        holder.bindItems(userList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cl_edit_button = itemView.findViewById(R.id.cl_edit_button) as ConstraintLayout

        fun bindItems(user: TaskModel) {


        }
    }


}