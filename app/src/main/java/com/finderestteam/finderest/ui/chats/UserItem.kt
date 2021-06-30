package com.finderestteam.finderest.ui.chats

import com.finderestteam.finderest.PersonData
import com.finderestteam.finderest.R
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.user_row_new_message.view.*

class UserItem(val user: PersonData): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.username_textview_new_message.text = user.userName
        Picasso.get().load(user.profileImageUrl).into(viewHolder.itemView.picture_user_new_message)
    }

    override fun getLayout(): Int {
        return R.layout.user_row_new_message
    }
}