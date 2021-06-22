package com.finderestteam.finderest.ui.find

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.finderestteam.finderest.PersonData
import com.finderestteam.finderest.R
import com.google.firebase.database.*
import java.util.*


class FindFragment : Fragment() {

    private lateinit var notificationsViewModel: FindViewModel
    private lateinit var listData: LinkedList<PersonData>
    private var it1 = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProviders.of(this).get(FindViewModel::class.java)
        val root = inflater.inflate(R.layout.person_banner, container, false)

        FirebaseDatabase.getInstance().getReference("users")
            .addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if(listData.size > 0)
                        listData.clear()
                    for (dt in dataSnapshot.children){
                        /*val person = dt.getValue(PersonData::class.java)
                        if (person != null) {
                            listData.add(person)
                        }*/
                        val name = dt.child("userName:").value.toString()
                        val mail = dt.child("userMail:").value.toString()
                        val pass = dt.child("userPassword:").value.toString()
                        val listOfInterests = dt.child("userListOfInterests:").value.toString().split(" ").toTypedArray()
                        val pd = PersonData(name, mail, pass, listOfInterests)
                        Log.e("MYTAG", "$name $mail $pass ${listOfInterests.toString()}")
                        listData.add(pd)
                    }
                    }
                    override fun onCancelled(databaseError: DatabaseError) { }
                })

        drawBanner(listData, it1)

        root.findViewById<Button>(R.id.LikeButton).setOnClickListener {
            drawBanner(listData, it1++)
        }

        root.findViewById<Button>(R.id.SkipButton).setOnClickListener { }

        return root
    }

    @SuppressLint("CutPasteId")
    private fun drawBanner(listDt: LinkedList<PersonData>, it: Int){

        val listView = requireActivity().findViewById<ListView>(R.id.BannerListOfInterests1)

        if (listDt.size == 0 || listDt.size == it + 1) {
            return
        }

        activity?.findViewById<TextView>(R.id.bannerPersonName)?.text = listDt[it].getName()
        activity?.findViewById<TextView>(R.id.bannerPersonMail)?.text = listDt[it].getMail()

        val adapter = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_list_item_1,
            listDt[it].getListOfInterests().split(" ").toTypedArray()
        )

        if (listView != null) {
            listView.adapter = adapter
        }
    }
}

