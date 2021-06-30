package com.finderestteam.finderest.ui.chats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.finderestteam.finderest.PersonData
import com.finderestteam.finderest.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.message_from_chatlog.view.*
import kotlinx.android.synthetic.main.message_to_chatlog.view.*

class ChatLogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        supportActionBar?.title = intent.getParcelableExtra<PersonData>(NewMessageActivity.USER_KEY)?.userName

        setData()

        send_button_activity_chat_log.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        val text = message_edittext_activity_chat_log.text.toString()
        val fromId = FirebaseAuth.getInstance().uid

        val user = intent.getParcelableExtra<PersonData>(NewMessageActivity.USER_KEY)
        val toId = user?.uid

        val ref = FirebaseDatabase.getInstance().getReference("/messages").push()

        val chatMessage = Message(ref.key, text, fromId, toId, System.currentTimeMillis()/1000)

        ref.setValue(chatMessage)
    }

    private fun setData() {
        val adapter = GroupAdapter<GroupieViewHolder>()

        adapter.add(MessageFromItem())
        adapter.add(MessageToItem())
        adapter.add(MessageFromItem())
        adapter.add(MessageToItem())
        adapter.add(MessageFromItem())
        adapter.add(MessageToItem())
        adapter.add(MessageFromItem())
        adapter.add(MessageToItem())
        adapter.add(MessageFromItem())
        adapter.add(MessageToItem())
        adapter.add(MessageFromItem())
        adapter.add(MessageToItem())

        recyclerview_chatlog.adapter = adapter
    }
}

class MessageFromItem(/*val user: PersonData*/): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textview_from_chatlog.text = "From Message ..."
      //Picasso.get().load(user.profileImageUrl).into(viewHolder.itemView.picture_user_chatlog)
    }

    override fun getLayout(): Int {
        return R.layout.message_from_chatlog
    }
}

class MessageToItem(/*val user: PersonData*/): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textview_to_chatlog.text = "To Message ..."
       // Picasso.get().load(user.profileImageUrl).into(viewHolder.itemView.picture_user_chatlog)
    }

    override fun getLayout(): Int {
        return R.layout.message_to_chatlog
    }
}