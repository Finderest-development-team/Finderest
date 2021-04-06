package com.finderestteam.finderest.ui.chats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is CHATS Fragment"
    }
    val text: LiveData<String> = _text
}