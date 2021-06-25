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
    private val list = mutableListOf<PersonData>()
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

        base.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "error: cant keep up", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot?.children?.forEach {
                        element -> list.add(element.getValue(PersonData::class.java)!!)
                }
            }
        })
        drawBanner(list, it1)

        root.findViewById<Button>(R.id.LikeButton).setOnClickListener {
            drawBanner(list, it1++)
        }

        root.findViewById<Button>(R.id.SkipButton).setOnClickListener { }

        return root
    }

    @SuppressLint("CutPasteId")
    private fun drawBanner(listDt: MutableList<PersonData>, it: Int){

        val listView = requireActivity().findViewById<ListView>(R.id.BannerListOfInterests1)

        if (listDt.size == 0 || listDt.size == it + 1) {
            return
        }

        activity?.findViewById<TextView>(R.id.bannerPersonName)?.text = listDt[it].userName
        activity?.findViewById<TextView>(R.id.bannerPersonMail)?.text = listDt[it].userMail

        val adapter = listDt[it].userListOfInterests?.split(" ")?.toTypedArray()?.let { it2 ->
            ArrayAdapter<String>(
                requireActivity(),
                android.R.layout.simple_list_item_1,
                it2
            )
        }

        if (listView != null) {
            listView.adapter = adapter
        }
    }
}




