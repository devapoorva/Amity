package com.apoorv.amity.webservices

import com.apoorv.amity.ui.login.model.LoginResponse
import com.apoorv.amity.ui.register.model.RegisterResponse
import com.apoorv.amity.ui.register.model.User
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
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
interface Api {
    @FormUrlEncoded
    @POST("api/register")
    fun register(@FieldMap params: HashMap<String, String>): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("oauth/token")
    fun login(@FieldMap params: HashMap<String, String>): Call<LoginResponse>

    @GET("api/v1/user")
    fun user(): Call<User>



}