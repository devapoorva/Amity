package com.apoorv.amity.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.apoorv.amity.R
import com.apoorv.amity.Utils.Constants
import com.apoorv.amity.data.AppSession
import com.apoorv.amity.databinding.ActivityLoginBinding
import com.apoorv.amity.ui.admin.AdminActivity
import com.apoorv.amity.ui.common.BaseActivity
import com.apoorv.amity.ui.login.viewmodel.LoginViewModel
import com.apoorv.amity.ui.register.RegisterActivity
import com.apoorv.amity.ui.user.UserActivity
import com.apoorv.amity.webservices.AuthResource
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
class LoginActivity :BaseActivity(){

    private lateinit var binding:ActivityLoginBinding
    private lateinit var viewModel:LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        setTitle("Login")
        binding.btnLogin.setOnClickListener { v->
            email = binding.edtEmail.text.toString()
            password = binding.edtPassword.text.toString()
            if(validate()){
                var params:HashMap<String,String> = HashMap()
                params["client_secret"] = Constants.CLIENT_SECRET
                params["grant_type"] = Constants.GRANT_TYPE
                params["scope"] = Constants.SCOPE
                params["client_id"] = Constants.CLIENT_ID.toString()
                params["username"] = email
                params["password"] = password
                viewModel.login(params)
                subscribe()
            }
        }

        binding.register.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }

    private fun subscribe() {
        viewModel.getLogin().observe(this, Observer { response->
            when(response.status){
                Resource.Companion.Status.LOADING->{
                    binding.btnLogin.showOrGone(false)
                    binding.progress.showOrGone(true)
                }
                Resource.Companion.Status.ERROR->{
                    binding.btnLogin.showOrGone(true)
                    binding.progress.showOrGone(false)
                    makeToast(response.message.toString())
                }
                Resource.Companion.Status.SUCCESS->{
                    AppSession.writeString(applicationContext,Constants.AUTH_TOKEN,response.data?.token_type+" "+response.data?.access_token)
                    viewModel.user()
                    subscribeUser()
                }
            }
        })
    }

    private fun subscribeUser() {
        viewModel.getuser().observe(this, Observer { response->
            when(response.status){
                AuthResource.Companion.Status.LOADING->{
                    binding.btnLogin.showOrGone(false)
                    binding.progress.showOrGone(true)
                }
                AuthResource.Companion.Status.ERROR->{
                    binding.btnLogin.showOrGone(true)
                    binding.progress.showOrGone(false)
                    makeToast(response.message.toString())
                }
                AuthResource.Companion.Status.NOT_AUTHENTICATED->{
                    binding.btnLogin.showOrGone(true)
                    binding.progress.showOrGone(false)
                    makeToast("Token Expired")
                }
                AuthResource.Companion.Status.AUTHENTICATED->{
                    makeToast("Login Successful")
                    AppSession.writeString(applicationContext,Constants.USER_NAME,response.data?.name)
                    AppSession.writeString(applicationContext,Constants.USER_EMAIL,response.data?.email)
                    var user_type:Int = response.data?.user_type ?: 1
                    AppSession.writeInteger(applicationContext,Constants.USER_TYPE,user_type)
                    startActivity(user_type)

                }
            }
        })
    }

    fun startActivity(user_type:Int){
        if(user_type==1){
            var intent = Intent(applicationContext, UserActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        else{
            var intent = Intent(applicationContext, AdminActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private var email:String = ""
    private var password:String = ""



    fun validate(): Boolean {
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
}