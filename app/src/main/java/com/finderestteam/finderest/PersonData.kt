package com.finderestteam.finderest

class PersonData{
    lateinit var userName: String
    lateinit var userMail: String
    lateinit var userPassword: String
    /*lateinit val userPhoto = _userPhoto*/
    lateinit var userListOfInterests: String

    constructor(){

    }

    constructor(_userName:String, _userMail:String, _userPassword:String, _userListOfInterests: Array<String>){
        this.userName = _userName
        this.userMail = _userMail
        this.userPassword =  _userPassword
        this.userListOfInterests = getRidOfUninteresting(_userListOfInterests)
    }

    private fun getRidOfUninteresting(_userListOfInterests: Array<String>): String {
        val arr = mutableListOf( "Sport", "Technologies", "Animals", "Gamer", "Education", "Parties", "Travelling", "Art", "Walking", "Books")
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
