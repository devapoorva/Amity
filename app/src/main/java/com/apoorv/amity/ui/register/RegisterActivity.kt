package com.apoorv.amity.ui.register

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apoorv.amity.R
import com.apoorv.amity.Utils.Constants
import com.apoorv.amity.data.AppSession
import com.apoorv.amity.databinding.ActivityRegisterBinding
import com.apoorv.amity.ui.common.BaseActivity
import com.apoorv.amity.ui.login.LoginActivity
import com.apoorv.amity.ui.register.viewmodel.RegisterViewModel
import com.apoorv.amity.webservices.Resource

/**
 * Created by Apoorv Vardhman on 8/13/2021
 *
 * @Email :  apoorv.vardhman@gmail.com
 * @Author :  Apoorv Vardhman (devapoorv.com)
 * @Linkedin :  https://in.linkedin.com/in/apoorv-vardhman
 * @Skype :  apoorv.vardhman
 * @Github : https://github.com/Apoorv-Vardhman
 * Contact :  +91 8434014444
 */
class RegisterActivity : BaseActivity(),AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    private var email:String = ""
    private var password:String = ""
    private var name:String  = ""
    private var type:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        setTitle("Register")
        ArrayAdapter.createFromResource(
            this,
            R.array.user_type,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spnType.adapter = adapter
        }

        binding.btnRegister.setOnClickListener {
            var user = binding.spnType.selectedItem.toString()
            if(user=="User"){
                type="1"
            }else{
                type="2"
            }
            name = binding.name.text.toString()
            email = binding.edtEmail.text.toString()
            password = binding.edtPassword.text.toString()
            if(validate()){
                var param:HashMap<String,String> = HashMap()
                param["name"] = name
                param["email"] = email
                param["password"] = password
                param["type"] = type
                viewModel.register(param)
                subscribe()

            }
        }

    }

    private fun subscribe() {
        viewModel.getRegister().observe(this, Observer { response->
            when(response.status){
                Resource.Companion.Status.LOADING->{
                    binding.btnRegister.showOrGone(false)
                    binding.progress.showOrGone(true)
                }
                Resource.Companion.Status.ERROR->{
                    binding.btnRegister.showOrGone(true)
                    binding.progress.showOrGone(false)
                    makeToast(response.message.toString())
                }
                Resource.Companion.Status.SUCCESS->{
                    if(response.data?.status == true){
                        var intent = Intent(applicationContext, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }else{
                        makeToast(response.data?.message.toString())
                        binding.btnRegister.showOrGone(true)
                        binding.progress.showOrGone(false)
                    }
                }
            }
        })
    }

    fun validate(): Boolean {
        if(name.isEmpty()){
            makeToast("Enter Name")
            return false
        }
        if(!isValidEmail(email)){
            makeToast("Enter valid email")
            return false
        }
        if(password.length<6){
            makeToast("Password should be min 6 character")
            return false
        }
        return true
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}