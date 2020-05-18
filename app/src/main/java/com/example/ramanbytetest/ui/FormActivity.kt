package com.example.ramanbytetest.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

import com.example.ramanbytetest.R
import com.example.ramanbytetest.clickListener.SaveSuccessListener
import com.example.ramanbytetest.databinding.ActivityFormBinding
import com.example.ramanbytetest.repository.NoteRepository
import kotlinx.android.synthetic.main.activity_form.*


class FormActivity : AppCompatActivity(),SaveSuccessListener {

    private var context: Context?=null
    private var activityFormBinder:ActivityFormBinding?=null
    private var edit:Boolean?=null
    private var first_name:String?=null
    private var last_name:String?=null
    private var phone:String?=null
    private var city:String?=null
    private var state:String?=null
    private var pin:String?=null
    private var id:Int=-1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context=this
        activityFormBinder = DataBindingUtil.setContentView(this, R.layout.activity_form)


        val intent = intent
         edit = intent.getBooleanExtra("edit",false)

        if (edit as Boolean){

            first_name= intent.getStringExtra("first_name")
            last_name= intent.getStringExtra("last_name")
            phone= intent.getStringExtra("phone")
            city= intent.getStringExtra("city")
            state= intent.getStringExtra("state")
            pin= intent.getStringExtra("pin")
            id= intent.getIntExtra("id",0)



            setData()
        }

        clickListener()

    }

    private fun setData() {

        activityFormBinder!!.etFirstName.setText(first_name)
        activityFormBinder!!.etLastName.setText(last_name)
        activityFormBinder!!.etPhone.setText(phone)
        activityFormBinder!!.etCity.setText(city)
        activityFormBinder!!.etState.setText(state)
        activityFormBinder!!.etPincode.setText(pin)

    }

    private fun clickListener() {

        activityFormBinder?.tvSave!!.setOnClickListener {

            val checkFieldResult=checkEmptyField()

            if (checkFieldResult){
                saveTask()
            }
        }

        activityFormBinder?.ivBack?.setOnClickListener{onBackPressed()}

        activityFormBinder?.tvCancel!!.setOnClickListener{

            finish()
        }

    }

  private  fun checkEmptyField():Boolean{

        var checkField=false

        if (activityFormBinder!!.etFirstName.text.isEmpty()){

            Toast.makeText(context, resources.getString(R.string.enter_first_name),Toast.LENGTH_SHORT).show()
            checkField=false
        }
        else if(activityFormBinder!!.etLastName.text.isEmpty()){

            Toast.makeText(context, resources.getString(R.string.enter_last_name),Toast.LENGTH_SHORT).show()
            checkField=false

        }
        else if(activityFormBinder!!.etPhone.text.isEmpty()){

            Toast.makeText(context, resources.getString(R.string.enter_phone_number),Toast.LENGTH_SHORT).show()
            checkField=false

        }
        else if(activityFormBinder!!.etCity.text.isEmpty()){

            Toast.makeText(context, resources.getString(R.string.enter_city),Toast.LENGTH_SHORT).show()
            checkField=false

        }
        else if(activityFormBinder!!.etState.text.isEmpty()){

            Toast.makeText(context, resources.getString(R.string.enter_state_name),Toast.LENGTH_SHORT).show()
            checkField=false

        }
        else if(activityFormBinder!!.etPincode.text.isEmpty()){

            Toast.makeText(context, resources.getString(R.string.enter_pin),Toast.LENGTH_SHORT).show()
            checkField=false

        }
        else {
            // save to database
            checkField=true

        }
        return checkField
    }

    private fun saveTask() {

        if (this.edit!!){


            val noteRepository = NoteRepository(applicationContext,this)

            noteRepository.updateTask(id,et_first_name.text.toString(),et_last_name.text.toString(),et_phone.text.toString(),et_city.text.toString(),et_state.text.toString(),et_pincode.text.toString())
        }

        else{

            val noteRepository = NoteRepository(applicationContext,this)
            noteRepository.insertTask(et_first_name.text.toString(), et_last_name.text.toString(),et_phone.text.toString(),et_city.text.toString(),et_state.text.toString(),et_pincode.text.toString())

        }

        }

    override fun saveSuccess() {

        val result = Intent()
        setResult(Activity.RESULT_OK, result)
        finish()

    }


}