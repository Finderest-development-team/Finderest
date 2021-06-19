package com.finderestteam.finderest.ui.find

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.finderestteam.finderest.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class FindFragment : Fragment() {

    private lateinit var notificationsViewModel: FindViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProviders.of(this).get(FindViewModel::class.java)
        val root = inflater.inflate(R.layout.person_banner, container, false)

        FirebaseDatabase.getInstance().getReference("finderest-b1bdf-default-rtdb").child("items")
            .child(FirebaseAuth.getInstance().currentUser.uid).addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) { }

            override fun onCancelled(databaseError: DatabaseError) { }
        })

        root.findViewById<Button>(R.id.SkipButton).setOnClickListener {  }

        root.findViewById<Button>(R.id.LikeButton).setOnClickListener {  }

        return root
    }
}