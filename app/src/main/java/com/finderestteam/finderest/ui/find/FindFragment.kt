package com.finderestteam.finderest.ui.find

import android.annotation.SuppressLint
import android.app.Person
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
import com.finderestteam.finderest.ui.chats.LatestMessages
import com.finderestteam.finderest.ui.chats.UserItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.squareup.picasso.Picasso

class FindFragment : Fragment() {

    private lateinit var notificationsViewModel: FindViewModel
    private val listDt = mutableListOf<PersonData>()
    private var it3 = 0
    private val base = FirebaseDatabase.getInstance().getReference("users")
    private val currentUser = LatestMessages.currentUser

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProviders.of(this).get(FindViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_find, container, false)

        getData()

        root.findViewById<Button>(R.id.SkipButton).setOnClickListener {
            drawNext()
        }

        root.findViewById<Button>(R.id.LikeButton).setOnClickListener {
            if (listDt.size >= it3){
                Log.d("ID_ARRAY", listDt[it3].uid.toString())
                match(listDt[it3])
            }

            drawNext()
        }

        return root
    }

    private fun drawNext() {

        it3++

        if (listDt.size <= it3){
            Toast.makeText(activity, "You have viewed all users", Toast.LENGTH_SHORT).show()
            it3 = 0
            drawBanner()
        }else{
            drawBanner()
        }
    }

    private fun match(user: PersonData) {

        val ref = FirebaseDatabase.getInstance().getReference("/likes/${currentUser?.uid}")

        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var flag = false

                snapshot.children.forEach {
                    val like = it.getValue(Like::class.java)

                    Log.d("COMPARE_ID_MATCH", like?.fromId.toString() + " and " + user.uid)

                    if (like != null && like.fromId == user.uid) {
                        addToFriends(user)
                        flag = true
                        it.ref.removeValue()
                    }
                }

                if (!flag) {
                    sendLike(user)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun addToFriends(user: PersonData) {
        val firstRef = FirebaseDatabase.getInstance().getReference("/friends/${currentUser?.uid}").child(user?.uid.toString())
        val secondRef = FirebaseDatabase.getInstance().getReference("/friends/${user?.uid}").child(currentUser?.uid.toString())

        firstRef.setValue(user)
        secondRef.setValue(currentUser)
    }

    private fun sendLike(user: PersonData) {
        val toId = user.uid
        val ref = FirebaseDatabase.getInstance().getReference("/likes/$toId")

        val newLike = Like(ref.key, currentUser?.uid, toId)

        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var flag = false

                snapshot.children.forEach {
                    val like = it.getValue(Like::class.java)

                    if (like != null && like.fromId == currentUser?.uid) {
                        flag = true
                    }
                }

                if (!flag) {
                    ref.push().setValue(newLike)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


    private fun getData(){
        base.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "error: cant keep up", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot?.children?.forEach { element ->
                    val user = element.getValue(PersonData::class.java)

                    if (user?.uid != currentUser?.uid) {
                        listDt.add(user!!)
                    }
                }

                drawBanner()
            }
        })
    }

    @SuppressLint("CutPasteId")
    private fun drawBanner(){

        if (listDt.isEmpty()) {
            Toast.makeText(activity, "There are no users yet", Toast.LENGTH_SHORT).show()
            return
        }

        if(FirebaseAuth.getInstance().currentUser.email == listDt[it3].userMail){
            it3++
        }

        if (listDt.size <= it3){
            Toast.makeText(activity, "You have viewed all users", Toast.LENGTH_SHORT).show()
            return
        }

        val listView = activity?.findViewById<ListView>(R.id.BannerListOfInterests1)
        val name = listDt[it3].userName
        val mail = listDt[it3].userMail
        val arr = listDt[it3].userListOfInterests?.split(" ")?.toTypedArray()
        val uiUser = listDt[it3].profileImageUrl

        if(name != null && mail != null && arr != null && uiUser != null){
            Picasso.get().load(uiUser).into(activity?.findViewById<ImageView>(R.id.bannerPhoto))
            activity?.findViewById<TextView>(R.id.bannerPersonName)?.text = name
            activity?.findViewById<TextView>(R.id.bannerPersonMail)?.text = mail
            val adapter = activity?.let { it2 -> ArrayAdapter<String>(it2, android.R.layout.simple_list_item_1, arr) }
            if (listView != null) {
                listView.adapter = adapter
            }
        }else{
            Toast.makeText(activity, "Cant get a user", Toast.LENGTH_SHORT).show()
        }
    }
}
