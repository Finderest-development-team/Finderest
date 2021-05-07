package com.finderestteam.finderest.ui.chats
import com.google.firebase.auth.FirebaseUser
import java.util.Date
class Message(_userName:String, _textMessage:String) {
    private val messageTime:Long = Date().time
    private val userName:String = _userName
    private val textMessage:String = _textMessage
    fun getName(): String {
        return this.userName
    }
    fun getMessage(): String {
        return this.textMessage
    }
    fun getTime(): Long {
        return this.messageTime
    }

}