package com.apoorv.amity.ui.login

import android.text.TextUtils
import android.util.Patterns
import com.apoorv.amity.ui.common.BaseActivity


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