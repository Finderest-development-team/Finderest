package com.finderestteam.finderest

class PersonData(_userName:String, _userMail:String, _userPassword:String, _userListOfInterests: Array<String>){
    private var userName = _userName
    private var userMail = _userMail
    private var userPassword =  _userPassword
    private var userListOfInterests = getRidOfUninteresting(_userListOfInterests)

    /*constructor(){
        this.userName = ""
        this.userMail = ""
        this.userPassword = ""
        this.userListOfInterests = ""
    }
    constructor(_userName:String, _userMail:String, _userPassword:String, _userListOfInterests: String){
        this.userName = _userName
        this.userMail = _userMail
        this.userPassword =  _userPassword
        this.userListOfInterests = _userListOfInterests
    }*/

    private fun getRidOfUninteresting(_userListOfInterests: Array<String>): String {
        val arr = mutableListOf( "Sport", "Technologies", "Animals", "Gamer", "Education", "Parties", "Travelling", "Art", "Walking", "Books")
        var str = ""

        if(_userListOfInterests[0] in arr)
        {
            println("in")
            for ((i, v) in _userListOfInterests.withIndex()){
                str += "${_userListOfInterests[i]} "
            }
            return str
        }

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
}
