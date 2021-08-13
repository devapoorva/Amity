package com.apoorv.amity.ui.upload

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.apoorv.amity.R
import com.apoorv.amity.Utils.Constants
import com.apoorv.amity.Utils.FileUtils
import com.apoorv.amity.data.AppSession
import com.apoorv.amity.databinding.ActivityUploadBinding
import com.apoorv.amity.ui.common.BaseActivity
import com.apoorv.amity.ui.upload.viewmodel.UploadViewModel
import com.apoorv.amity.webservices.AuthResource
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.Part
import java.io.File


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
class UploadActivity : BaseActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var viewModel:UploadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_upload)
        viewModel = ViewModelProvider(this).get(UploadViewModel::class.java)

        setTitle("Upload Question")
        ArrayAdapter.createFromResource(
            this,
            R.array.question_type,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spnType.adapter = adapter
        }

        binding.btnPdf.setOnClickListener { v->
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "application/pdf"
            resultLauncher.launch(intent)
        }

        binding.btnBanner.setOnClickListener { v->
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "application/*"
            resultBanner.launch(intent)
        }

        binding.btnUpload.setOnClickListener {
            var t = binding.edtTitle.text.toString()

            var type_check = binding.spnType.selectedItem.toString()
            var type_a:String = ""
            if(type_check=="New"){
                type_a="1"
            }else{
                type_a="2"
            }
            type = type_a.toRequestBody("text/plain".toMediaTypeOrNull())
            title = t.toRequestBody("text/plain".toMediaTypeOrNull())

            viewModel.upload(title,pdf,banner,type)
            subscribe()
        }

    }

    private fun subscribe() {
        viewModel.uploadresponse().observe(this, Observer { response->
            when(response.status){
                AuthResource.Companion.Status.LOADING->{
                    binding.btnUpload.showOrGone(false)
                    binding.progress.showOrGone(true)
                }
                AuthResource.Companion.Status.ERROR->{
                    binding.btnUpload.showOrGone(true)
                    binding.progress.showOrGone(false)
                    makeToast(response.message.toString())
                }
                AuthResource.Companion.Status.NOT_AUTHENTICATED->{
                    binding.btnUpload.showOrGone(true)
                    binding.progress.showOrGone(false)
                    makeToast("Token Expired")
                }
                AuthResource.Companion.Status.AUTHENTICATED->{
                    makeToast("Upload Successful")

                }
            }
        })
    }

    lateinit var title: RequestBody
    lateinit var pdf: RequestBody
    lateinit var banner: RequestBody
    lateinit var type: RequestBody

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            var intent: Intent? = result.data

            val uri: Uri? = intent?.data
            val uriString: String = uri.toString()
            val path: String? = FileUtils.getPath(this, uri!!)
            val file = File(path)
            pdf = file.asRequestBody("pdf/*".toMediaTypeOrNull())
        }
    }

    var resultBanner = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            var intent: Intent? = result.data

            val uri: Uri? = intent?.data
            val uriString: String = uri.toString()
            val path: String? = FileUtils.getPath(this, uri!!)
            val file = File(path)
            banner = file.asRequestBody("image/*".toMediaTypeOrNull())
        }
    }

}