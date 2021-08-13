package com.apoorv.amity.data

import android.content.Context
import android.content.SharedPreferences

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
class AppSession {
    companion object{
        val FCM_TOKEN = "FCM_TOKEN"

        private val PREF_NAME = "MY_PREF"
        private val MODE = Context.MODE_PRIVATE
        val PERMISSION_ENABLED = "PERMISSION_ENABLED"

        fun clearAllPrefs(context: Context) {
            getEditor(context).clear().commit()
        }

        /**
         * @param context
         * @param key
         * @param value
         */
        fun writeBoolean(context: Context, key: String?, value: Boolean) {
            getEditor(context).putBoolean(key, value).commit()
        }

        /**
         * @param context
         * @param key
         * @param defValue
         * @return
         */
        fun readBoolean(context: Context, key: String?, defValue: Boolean): Boolean {
            return getPreferences(context).getBoolean(key, defValue)
        }

        /**
         * @param context
         * @param key
         * @param value
         */
        fun writeInteger(context: Context, key: String?, value: Int) {
            getEditor(context).putInt(key, value).commit()
        }


        /**
         * @param context
         * @param key
         * @param defValue
         * @return
         */
        fun readInteger(context: Context, key: String?, defValue: Int): Int {
            return getPreferences(context).getInt(key, defValue)
        }

        /**
         * @param context
         * @param key
         * @param value
         */
        fun writeString(context: Context, key: String?, value: String?) {
            getEditor(context).putString(key, value).commit()
        }

        /**
         * @param context
         * @param key
         * @param defValue
         * @return
         */
        fun readString(context: Context, key: String?, defValue: String?): String? {
            return getPreferences(context).getString(key, defValue)
        }

        /**
         * @param context
         * @param key
         * @param value
         */
        fun writeFloat(context: Context, key: String?, value: Float) {
            getEditor(context).putFloat(key, value).commit()
        }

        /**
         * @param context
         * @param key
         * @param defValue
         * @return
         */
        fun readFloat(context: Context, key: String?, defValue: Float): Float {
            return getPreferences(context).getFloat(key, defValue)
        }

        /**
         * @param context
         * @param key
         * @param value
         */
        fun writeLong(context: Context, key: String?, value: Long) {
            getEditor(context).putLong(key, value).commit()
        }

        /**
         * @param context
         * @param key
         * @param defValue
         * @return
         */
        fun readLong(context: Context, key: String?, defValue: Long): Long {
            return getPreferences(context).getLong(key, defValue)
        }

        /**
         * @param context
         * @return
         */
        fun getPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(PREF_NAME, MODE)
        }

        /**
         * @param context
         * @return
         */
        fun getEditor(context: Context): SharedPreferences.Editor {
            return getPreferences(context).edit()
        }
    }
}