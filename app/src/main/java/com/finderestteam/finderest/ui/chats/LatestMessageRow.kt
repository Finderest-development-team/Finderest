package com.finderestteam.finderest.ui.chats

import com.finderestteam.finderest.PersonData
import com.finderestteam.finderest.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.latest_message.view.*

class LatestMessageRow(val message: Message): Item<GroupieViewHolder>() {

    var chatPartnerUser: PersonData? = null

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.itemView.message_textview_latest_message.text = message.text

        val chatPartnerId: String
        if (message.fromId == FirebaseAuth.getInstance().uid)
        {
            chatPartnerId = message.toId.toString()
        }
        else {
            chatPartnerId = message.fromId.toString()
        }


        val ref = FirebaseDatabase.getInstance().getReference("/users/$chatPartnerId")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                chatPartnerUser = snapshot.getValue(PersonData::class.java)
                viewHolder.itemView.username_textview_latest_message.text = chatPartnerUser?.userName
                Picasso.get().load(chatPartnerUser?.profileImageUrl).into(viewHolder.itemView.imageview_latest_message)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }

    override fun getLayout(): Int {
        return R.layout.latest_message
    }
}