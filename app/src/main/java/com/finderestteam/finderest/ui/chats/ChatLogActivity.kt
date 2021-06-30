package com.finderestteam.finderest.ui.chats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.finderestteam.finderest.PersonData
import com.finderestteam.finderest.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_chat_log.*

class ChatLogActivity : AppCompatActivity() {

    private val adapter = GroupAdapter<GroupieViewHolder>()
    private var toUser: PersonData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        toUser = intent.getParcelableExtra<PersonData>(NewMessageActivity.USER_KEY)
        supportActionBar?.title = toUser?.userName

        recyclerview_chatlog.adapter = adapter

        listenForMessages()

        send_button_activity_chat_log.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        val text = message_edittext_activity_chat_log.text.toString()
        val fromId = FirebaseAuth.getInstance().uid
        val toId = toUser?.uid

        val ref = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId").push()
        val toRef = FirebaseDatabase.getInstance().getReference("/user-messages/$toId/$fromId").push()

        val chatMessage = Message(ref.key, text, fromId, toId, System.currentTimeMillis()/1000)

        ref.setValue(chatMessage).addOnSuccessListener {
            message_edittext_activity_chat_log.text.clear()
            recyclerview_chatlog.scrollToPosition(adapter.itemCount - 1)
        }
        toRef.setValue(chatMessage)

        val latestMessageRef = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId/$toId")
        latestMessageRef.setValue(chatMessage)

        val latestMessageToRef = FirebaseDatabase.getInstance().getReference("/latest-messages/$toId/$fromId")
        latestMessageToRef.setValue(chatMessage)
    }

    private fun listenForMessages() {
        val fromId = FirebaseAuth.getInstance().uid
        val toId = toUser?.uid
        val ref = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId")

        ref.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val chatMessage = snapshot.getValue(Message::class.java)
                if (chatMessage != null)
                {
                    if (chatMessage.fromId != FirebaseAuth.getInstance().uid) {
                        adapter.add(MessageFromItem(chatMessage, toUser!!))
                    }
                    else {
                        val currentUser = LatestMessages.currentUser
                        adapter.add(MessageToItem(chatMessage, currentUser!!))
                    }
                }

                recyclerview_chatlog.scrollToPosition(adapter.itemCount - 1)
            }

            override fun onCancelled(error: DatabaseError) {

            }


            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }
        })
    }
}
