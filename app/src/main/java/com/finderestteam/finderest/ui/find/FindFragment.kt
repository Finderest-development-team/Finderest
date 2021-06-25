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
    private lateinit var listData: MutableList<String>
    private var it1 = 0
    private val base = FirebaseDatabase.getInstance().getReference("users")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProviders.of(this).get(FindViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_find, container, false)

        /*base.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "error: cant keep up", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(listData.size > 0)
                    listData.clear()
                for(it in snapshot.children){
                    val person = it.child("name").value
                    if(person != null)
                        listData.add(person.toString())
                }
            }
        })
        drawBanner(listData, it1)

        root.findViewById<Button>(R.id.LikeButton).setOnClickListener {
            drawBanner(listData, it1++)
        }

        root.findViewById<Button>(R.id.SkipButton).setOnClickListener { }*/

        return root
    }

    @SuppressLint("CutPasteId")
    private fun drawBanner(listDt: MutableList<PersonData>, it: Int){

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




