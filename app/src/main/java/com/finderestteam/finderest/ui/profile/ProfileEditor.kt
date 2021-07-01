package com.finderestteam.finderest.ui.profile

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.finderestteam.finderest.R
import com.finderestteam.finderest.ui.chats.LatestMessages
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.Button5
import kotlinx.android.synthetic.main.fragment_profile_editor.view.*

class ProfileEditor:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        val root = inflater.inflate(R.layout.fragment_profile_editor, container, false)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        root.button11.setOnClickListener { NavHostFragment.findNavController(this).navigate(R.id.navigation_profile) }

        val imageView = root.findViewById<ImageButton>(R.id.imageButton)
        val nameView  = root.findViewById<TextView>(R.id.textView8)
        val mailView  = root.findViewById<TextView>(R.id.textView)

        Picasso.get().load(LatestMessages.currentUser?.profileImageUrl).into(imageView)
        nameView.text = LatestMessages.currentUser?.userName
        mailView.text = LatestMessages.currentUser?.userMail

        return root
    }
}