package com.apoorv.amity.webservices

import com.apoorv.amity.Utils.Constants
import com.apoorv.amity.VardhmanApp
import com.apoorv.amity.data.AppSession
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
class AuthRetrofit {
    private val retrofit: Retrofit
    init {
        var token: String? = AppSession.readString(VardhmanApp.applicationContext(), Constants.AUTH_TOKEN,"")
        var logging = LoggingInterceptor.Builder()
            .log(Platform.WARN)
            .setLevel(Level.BASIC)
            .addHeader("Accept","application/json")
            .addHeader("Authorization", token.toString())
            .request("request")
            .response("response")
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(OkHttpClient.Builder().addInterceptor(logging).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object{
        private var instance:AuthRetrofit? = null
        fun getInstance():AuthRetrofit?{
            return AuthRetrofit()
        }
    }

    fun <T> buildRetrofit(serviceType:Class<T>): T{
        return retrofit.create(serviceType)
    }
}