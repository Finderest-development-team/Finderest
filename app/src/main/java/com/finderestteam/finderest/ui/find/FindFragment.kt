package com.finderestteam.finderest.ui.find

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.finderestteam.finderest.R
import com.google.firebase.database.*


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

        val list: ListView = root.findViewById(R.id.BannerListOfInterests1)
        FirebaseDatabase.getInstance().getReference("finderest-b1bdf-default-rtdb")
            .child("items").addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    root.findViewById<TextView>(R.id.bannerPersonName).text =
                        dataSnapshot.child("name:").value.toString()
                    root.findViewById<TextView>(R.id.bannerPersonMail).text =
                        dataSnapshot.child("mail:").value.toString()
                    /*var arr = dataSnapshot.child("listOfInterests:").value.toString().split(" ")
                        .toTypedArray()
                    val adapter = activity?.let {
                        ArrayAdapter<String>(
                            it,
                            R.layout.person_banner, arr
                        )
                    }
                    list.adapter = adapter*/
                    }
                    override fun onCancelled(databaseError: DatabaseError) {}
                })
        root.findViewById<Button>(R.id.LikeButton).setOnClickListener {  }

        root.findViewById<Button>(R.id.SkipButton).setOnClickListener { }

        return root
    }
}
