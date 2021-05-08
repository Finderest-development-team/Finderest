package com.finderestteam.finderest

class PersonData(_userName:String, _userMail:String, _userPassword:String, _userListOfInterests: Array<String>, _userPhoto: String) {
    private val userName = _userName
    private val userMail = _userMail
    private val userPassword = _userPassword
    /*private val userPhoto = _userPhoto*/
    private val userListOfInterests = getRidOfUninteresting(_userListOfInterests)

    private fun getRidOfUninteresting(_userListOfInterests: Array<String>): String {
        val arr = mutableListOf<String> ( "Sport", "Technologies", "Animals", "Gamer", "Education", "Parties", "Travelling", "Art", "Walking", "Books")
        var str = ""
        for ((i, v) in _userListOfInterests.withIndex()){
            if(v.toBoolean())
                str += "${arr[i]} "
        }
        return str
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
    fun getListOfInterests(): String {
        return userListOfInterests
    }
    /*fun getPhoto(): String {
        return userPhoto
    }*/
}
