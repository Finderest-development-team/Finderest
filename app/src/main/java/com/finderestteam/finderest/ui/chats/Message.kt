package com.finderestteam.finderest.ui.chats


class Message(var id: String? = null,
              var text: String? = null,
              var fromId: String? = null,
              var toId: String? = null,
              var timestamp: Long? = null){}