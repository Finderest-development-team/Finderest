package com.finderestteam.finderest.ui.chats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.finderestteam.finderest.PersonData
import com.finderestteam.finderest.R
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.activity_new_message.*
import kotlinx.android.synthetic.main.user_row_new_message.view.*

class ChatLogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        val adapter = GroupAdapter<GroupieViewHolder>()

        recyclerview_chatlog.adapter = adapter
    }
}

class MessageFrom(val user: PersonData): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
      //  Picasso.get().load(user.profileImageUrl).into(viewHolder.itemView.picture_user_chatlog)
    }

    override fun getLayout(): Int {
        return R.layout.message_from_chatlog
    }
}

class MessageTo(val user: PersonData): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
       // Picasso.get().load(user.profileImageUrl).into(viewHolder.itemView.picture_user_chatlog)
    }

    override fun getLayout(): Int {
        return R.layout.message_to_chatlog
    }
}