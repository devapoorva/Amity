package com.apoorv.amity.ui.upload.viewmodel

import androidx.lifecycle.MutableLiveData
import com.apoorv.amity.ui.common.BaseViewModel
import com.apoorv.amity.ui.register.model.RegisterResponse
import com.apoorv.amity.ui.upload.model.UploadResponse
import com.apoorv.amity.webservices.AuthResource
import com.apoorv.amity.webservices.Resource
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
class UploadViewModel : BaseViewModel() {
    private var uploadLivedata: MutableLiveData<AuthResource<UploadResponse>> = MutableLiveData()

    fun uploadresponse(): MutableLiveData<AuthResource<UploadResponse>> {
        return uploadLivedata
    }

    fun upload(title: RequestBody,pdf: RequestBody,banner: RequestBody,type: RequestBody){
        uploadLivedata.postValue(AuthResource.loading(null))
        api.upload(title,pdf,banner,type).enqueue(object: Callback<UploadResponse>{
            override fun onResponse(
                call: Call<UploadResponse>,
                response: Response<UploadResponse>
            ) {
                if(response.code()==200){
                    uploadLivedata.postValue(AuthResource.success(response.body()))
                }else if(response.code()==401){
                    uploadLivedata.postValue(AuthResource.logout(null))
                }else{
                    uploadLivedata.postValue(AuthResource.error("Something went wrong",null))
                }
            }

            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                uploadLivedata.postValue(AuthResource.error("Something went wrong",null))
            }

        })
    }
}