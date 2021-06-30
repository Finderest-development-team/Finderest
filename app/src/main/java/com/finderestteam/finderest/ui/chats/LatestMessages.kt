package com.finderestteam.finderest.ui.chats

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.finderestteam.finderest.R
import androidx.recyclerview.widget.DividerItemDecoration
import com.finderestteam.finderest.PersonData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_latest_messages.view.*


class LatestMessages : Fragment() {

    private var adapter = GroupAdapter<GroupieViewHolder>()
    private val latestMessagesMap = HashMap<String, Message>()

    companion object {
        var currentUser: PersonData? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fetchCurrentUser()

        val root = inflater.inflate(R.layout.activity_latest_messages, container, false)
        root.newChat.setOnClickListener {
            val int = Intent(activity, NewMessageActivity::class.java)
            startActivity(int)
        }

        root.recyclerview_latest_messages.adapter = adapter
        root.recyclerview_latest_messages.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        adapter.setOnItemClickListener { item, view ->
            val row = item as LatestMessageRow
            val intent = Intent(view.context, ChatLogActivity::class.java)
            intent.putExtra(NewMessageActivity.USER_KEY, row.chatPartnerUser)
            startActivity(intent)
        }

        listenForLatestMessages()

        return root
    }



    private fun listenForLatestMessages() {
        val fromId = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId")

        ref.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val chatMessage = snapshot.getValue(Message::class.java) ?: return

                latestMessagesMap[snapshot.key!!] = chatMessage
                refreshRecyclerView()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val chatMessage = snapshot.getValue(Message::class.java) ?: return
                latestMessagesMap[snapshot.key!!] = chatMessage
                refreshRecyclerView()
            }

            override fun onCancelled(error: DatabaseError) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }
        })
    }

    private fun refreshRecyclerView() {
        adapter.clear()
        latestMessagesMap.values.forEach {
            if (it.toId != currentUser?.uid)
            {
                adapter.add(LatestMessageRow(it))
            }
        }
    }

    private fun fetchCurrentUser() {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                currentUser = snapshot.getValue(PersonData::class.java)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}
