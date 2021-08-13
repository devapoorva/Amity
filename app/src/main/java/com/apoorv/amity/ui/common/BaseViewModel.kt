package com.apoorv.amity.ui.common

import androidx.lifecycle.ViewModel
import com.apoorv.amity.webservices.Api
import com.apoorv.amity.webservices.AuthRetrofit

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
open class BaseViewModel: ViewModel() {
    lateinit var api: Api
    init {
        var retrofit = AuthRetrofit.getInstance()
        if (retrofit != null) {
            api = retrofit.buildRetrofit(Api::class.java)
        }
    }
}