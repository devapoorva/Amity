package com.apoorv.amity.ui.register.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apoorv.amity.ui.login.model.LoginResponse
import com.apoorv.amity.ui.register.model.RegisterResponse
import com.apoorv.amity.webservices.Api
import com.apoorv.amity.webservices.Resource
import com.apoorv.amity.webservices.RetrofitInstance
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
class RegisterViewModel : ViewModel() {

    private var registerLiveData: MutableLiveData<Resource<RegisterResponse>> = MutableLiveData()

    fun getRegister(): MutableLiveData<Resource<RegisterResponse>> {
        return registerLiveData
    }

    fun register( params: HashMap<String, String>){
        val retrofitInstance = RetrofitInstance.getInstance()
        val api = retrofitInstance?.buildRetrofit(Api::class.java)
        registerLiveData.postValue(Resource.loading(null))
        api?.register(params)?.enqueue(object :Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if(response.code()==200){
                    registerLiveData.postValue(Resource.success(response.body()))
                }else if(response.code()==401){
                    registerLiveData.postValue(Resource.error("Enter Valid Email",null))
                }else{
                    registerLiveData.postValue(Resource.error("Something went wrong",null))
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                registerLiveData.postValue(Resource.error(t.message.toString(),null))
            }

        })
    }
}