package com.apoorv.amity.webservices

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
class Resource<T>(status: Status, data: T?, message: String?){
    var status: Status? = null
    var data: T? = null
    var message: String? = null

    init {
        this.status = status
        this.data = data
        this.message = message
    }

    companion object{
        fun <T> success(data: T?): Resource<T>? {
            return Resource<T>(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T>? {
            return Resource<T>(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T>? {
            return Resource<T>(Status.LOADING, data, null)
        }

        enum class Status {
            SUCCESS, ERROR, LOADING
        }
    }
}