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
class AuthResource<T>(status: Status, data: T?, message: String?){
    var status: Status? = null
    var data: T? = null
    var message: String? = null

    init {
        this.status = status
        this.data = data
        this.message = message
    }

    companion object{
        fun <T> success(data: T?): AuthResource<T>? {
            return AuthResource<T>(Status.AUTHENTICATED, data, null)
        }

        fun <T> error(msg: String, data: T?): AuthResource<T>? {
            return AuthResource<T>(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): AuthResource<T>? {
            return AuthResource<T>(Status.LOADING, data, null)
        }

        fun <T> logout(data: T?): AuthResource<T>? {
            return AuthResource(Status.NOT_AUTHENTICATED, data, null)
        }

        enum class Status {
            AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED
        }
    }
}