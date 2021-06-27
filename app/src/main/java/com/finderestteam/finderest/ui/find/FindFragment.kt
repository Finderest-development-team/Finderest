package com.finderestteam.finderest.ui.find

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.finderestteam.finderest.PersonData
import com.finderestteam.finderest.R
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import java.util.*


class FindFragment : Fragment() {

    private lateinit var notificationsViewModel: FindViewModel
    private val listDt = mutableListOf<PersonData>()
    private var it3 = 0
    private val base = FirebaseDatabase.getInstance().getReference("users")
    private val ref = FirebaseStorage.getInstance().getReference("images")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProviders.of(this).get(FindViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_find, container, false)

        getDataBase()
        drawBanner()

        root.findViewById<Button>(R.id.LikeButton).setOnClickListener {
            drawBanner()
        }

        root.findViewById<Button>(R.id.SkipButton).setOnClickListener { }

        return root
    }

    private fun getDataBase(){
        base.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "error: cant keep up", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot?.children?.forEach {
                        element -> listDt.add(element.getValue(PersonData::class.java)!!)
                }
            }
        })
    }
    private fun getAndSetPhoto(mail: String): Boolean {
        val path = ref.child("$mail").downloadUrl
        path.addOnSuccessListener{
            activity?.findViewById<ImageView>(R.id.bannerPhoto)?.setImageURI(it)
        }
        return path.isSuccessful
    }

    @SuppressLint("CutPasteId")
    private fun drawBanner(){

        if (listDt.isEmpty()) {
            Toast.makeText(activity, "There are no users yet", Toast.LENGTH_SHORT).show()
            return
        }
        if (listDt.size <= it3){
            Toast.makeText(activity, "You have viewed all users", Toast.LENGTH_SHORT).show()
            it3 = 0
        }

        val listView = activity?.findViewById<ListView>(R.id.BannerListOfInterests1)
        val name = listDt[it3].userName
        val mail = listDt[it3].userMail
        val arr = listDt[it3].userListOfInterests?.split(" ")?.toTypedArray()

        if(name != null && mail != null && arr != null /*&& getAndSetPhoto(mail)*/){
            activity?.findViewById<TextView>(R.id.bannerPersonName)?.text = name
            activity?.findViewById<TextView>(R.id.bannerPersonMail)?.text = mail
            val adapter = activity?.let { it2 -> ArrayAdapter<String>(it2, android.R.layout.simple_list_item_1, arr) }
            if (listView != null) {
                listView.adapter = adapter
            }
        }else{
            Toast.makeText(activity, "Cant get a user", Toast.LENGTH_SHORT).show()
        }
        it3++
    }
}




