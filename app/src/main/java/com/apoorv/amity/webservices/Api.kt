package com.apoorv.amity.webservices

import com.apoorv.amity.ui.login.model.LoginResponse
import com.apoorv.amity.ui.register.model.RegisterResponse
import com.apoorv.amity.ui.register.model.User
import com.apoorv.amity.ui.upload.model.UploadResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*


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

    @Multipart
    @POST("api/v1/upload")
    fun upload(
        @Part("title") title: RequestBody,
        @Part("pdf") pdf: RequestBody,
        @Part("banner") banner: RequestBody,
        @Part("type") type: RequestBody,
    ): Call<UploadResponse>


}