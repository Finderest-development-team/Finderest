package com.finderestteam.finderest.ui.chats
import java.util.Date
class Message(_userName:String, _textMessage:String) {
    private val messageTime:Long = Date().time
    val userName:String = _userName
    val textMessage:String = _textMessage
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