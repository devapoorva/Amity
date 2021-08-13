package com.apoorv.amity.ui.user

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.apoorv.amity.R
import com.apoorv.amity.Utils.Constants
import com.apoorv.amity.data.AppSession
import com.apoorv.amity.databinding.ActivityUserBinding
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
class UserActivity:BaseActivity() {
    private lateinit var binding:ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user)

        binding.tvEmail.setText(AppSession.readString(applicationContext,Constants.USER_EMAIL,""))
        binding.tvName.setText(AppSession.readString(applicationContext,Constants.USER_NAME,""))

        binding.btnLogout.setOnClickListener {
            logout()
        }
    }
}