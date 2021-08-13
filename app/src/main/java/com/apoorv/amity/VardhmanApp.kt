package com.apoorv.amity

import android.app.Activity
import android.app.Application
import android.content.Intent
import kotlin.system.exitProcess

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
class VardhmanApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object{
        private var instance: VardhmanApp? = null

        fun applicationContext(): VardhmanApp{
            return instance as VardhmanApp
        }

        fun exit(currentActivity: Activity){
            var intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            currentActivity.startActivity(intent)
            currentActivity.finish()
            exitProcess(0)
        }
    }
}