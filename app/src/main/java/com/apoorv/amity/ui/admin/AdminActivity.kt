package com.apoorv.amity.ui.admin

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.apoorv.amity.R
import com.apoorv.amity.databinding.ActivityAdminBinding
import com.apoorv.amity.ui.common.BaseActivity
import com.apoorv.amity.ui.upload.UploadActivity

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
class AdminActivity : BaseActivity() {
    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin)
        setTitle("Admin")
        binding.btnLogout.setOnClickListener {
            logout()
        }

        binding.btnUpload.setOnClickListener {
            startActivity(Intent(this,UploadActivity::class.java))
        }
    }

}