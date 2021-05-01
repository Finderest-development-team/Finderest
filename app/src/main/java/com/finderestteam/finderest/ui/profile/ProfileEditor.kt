package com.finderestteam.finderest.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.finderestteam.finderest.R
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.Button5
import kotlinx.android.synthetic.main.fragment_profile_editor.view.*

class ProfileEditor:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile_editor, container, false)
        root.button11.setOnClickListener { NavHostFragment.findNavController(this).navigate(R.id.navigation_profile) }
        return root
    }
}