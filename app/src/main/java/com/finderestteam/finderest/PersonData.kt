package com.finderestteam.finderest

class PersonData(_userName:String, _userMail:String, _userPassword:String, _userListOfInterests: Array<String>/*, _userPhoto:URI?*/) {
    private val userName = _userName
    private val userMail = _userMail
    private val userPassword = _userPassword
    private val userListOfInterests = _userListOfInterests
    //private val userPhoto = _userPhoto
    fun getName(): String {
        return userName
    }
    fun getMail(): String {
        return userMail
    }
    fun getPassword(): String {
        return userPassword
    }
    fun getListOfInterests(): Array<String> {
        return userListOfInterests
    }
    /*fun getPhoto(): URI? {
        return userPhoto
    }*/
}