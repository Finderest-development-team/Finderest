package com.finderestteam.finderest.ui.chats

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat.format
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.finderestteam.finderest.PersonData
import com.finderestteam.finderest.R
import com.finderestteam.finderest.ui.registation.RegistrationPart2
import com.firebase.ui.database.FirebaseListAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_latest_messages.view.*
import android.text.format.DateFormat

class Chat : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_chat_with_person, container, false)
        val listOfMessages = root.findViewById<ListView>(R.id.ListOfMessages)
        var adapter = object: FirebaseListAdapter<Message>(
            activity,
            Message::class.java,
            R.layout.message,
            FirebaseDatabase.getInstance().getReference("finderest-b1bdf-default-rtdb")
                .child("Messages")
        ){
            override fun populateView(v: View?, model: Message?, position: Int) {
                if (v != null && model != null) {
                    v.findViewById<TextView>(R.id.user_message).text = model.getMessage()
                    v.findViewById<TextView>(R.id.time).text =
                        DateFormat.format("dd-mm-yyyy HH:mm:ss", model.getTime())
                    v.findViewById<TextView>(R.id.user_name).text = model.getName()
                }
            }
        }
        listOfMessages.adapter = adapter
        root.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.ButtonSend).setOnClickListener {
            val textField = root.findViewById<EditText>(R.id.messageField)
            if(textField.text.toString() != ""){
                FirebaseDatabase.getInstance().reference.child("Messages").push()
                    .setValue(
                        Message(FirebaseAuth.getInstance().currentUser.email,textField.text.toString())
                    )
            }
            textField.setText("")
        }
        return root
    }
}