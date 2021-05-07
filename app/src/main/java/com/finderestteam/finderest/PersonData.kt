package com.finderestteam.finderest

import android.net.Uri
import androidx.core.net.toUri

class PersonData(_userName:String, _userMail:String, _userPassword:String, _userListOfInterests: Array<String?>, _userPhoto: String) {
    private val userName = _userName
    private val userMail = _userMail
    private val userPassword = _userPassword
    //private val userPhoto = _userPhoto.toUri()

    private val  _userListOfInterests = _userListOfInterests
    private val userListOfInterests = getRidOfUninteresting()

    private fun getRidOfUninteresting():List<String>{
        val arr = mutableListOf<String> ( "Sport", "Technologies", "Animals", "Gamer", "Education", "Parties", "Travelling", "Art", "Walking")
        for ((i, v) in this._userListOfInterests.withIndex()){
            if (v != null) {
                if(!v.toBoolean())
                    arr.removeAt(i)
            }
        }
        return arr
    }
    fun getName(): String {
        return userName
    }
    fun getMail(): String {
        return userMail
    }
    fun getPassword(): String {
        return userPassword
    }
    fun getListOfInterests(): List<String> {
        return userListOfInterests
    }
    /*fun getPhoto(): Uri {
        return userPhoto
    }*/
}
