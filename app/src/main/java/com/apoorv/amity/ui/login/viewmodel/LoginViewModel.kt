package com.apoorv.amity.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apoorv.amity.ui.login.model.LoginResponse
import com.apoorv.amity.ui.register.model.User
import com.apoorv.amity.webservices.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

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
class LoginViewModel : ViewModel() {
    var loginLiveData: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    var userLiveData: MutableLiveData<AuthResource<User>> = MutableLiveData()

    fun getLogin(): MutableLiveData<Resource<LoginResponse>> {
        return loginLiveData
    }

    fun getuser(): MutableLiveData<AuthResource<User>> {
        return userLiveData
    }

    fun login(param : HashMap<String, String> ){
        val retrofitInstance = RetrofitInstance.getInstance()
        val api = retrofitInstance?.buildRetrofit(Api::class.java)
        var call = api?.login(param)
        loginLiveData.postValue(Resource.loading(null))
        if(call!=null){
            call.enqueue(object : Callback<LoginResponse>{
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if(response.code()==200){
                        loginLiveData.postValue(Resource.success(response.body()))
                    }else if((response.code()>=400) and (response.code()<500)){
                        loginLiveData.postValue(Resource.error("Enter Valid Email or password",null))
                    }else{
                        loginLiveData.postValue(Resource.error("Something went wrong",null))
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    loginLiveData.postValue(Resource.error(t.message.toString(),null))
                }

            })
        }
    }

    fun user(){
        var authRet = AuthRetrofit.getInstance()
        val api = authRet?.buildRetrofit(Api::class.java)
        api?.user()?.enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.code()==200){
                    userLiveData.postValue(AuthResource.success(response.body()))
                }else if(response.code()==401){
                    userLiveData.postValue(AuthResource.logout(null))
                }else{
                    userLiveData.postValue(AuthResource.error("Something went wrong",null))
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                userLiveData.postValue(AuthResource.error(t.message.toString(),null))
            }

        })
    }



}