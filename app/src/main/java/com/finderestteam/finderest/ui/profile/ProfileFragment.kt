package com.finderestteam.finderest.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import com.finderestteam.finderest.R
import kotlinx.android.synthetic.main.fragment_profile.view.*
class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val imageView = root.findViewById<ImageButton>(R.id.imageButton)
        root.Button5.setOnClickListener {
            //оно не работает как надо, нет анимации я хз почему
            val extras = FragmentNavigatorExtras(
                imageView to "imageView"
            )
            NavHostFragment.findNavController(this).navigate(R.id.navigation_profile_editor, null, null, extras)
        }

        return root
    }
}