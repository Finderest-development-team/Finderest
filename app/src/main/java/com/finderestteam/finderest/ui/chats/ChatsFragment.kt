package com.finderestteam.finderest.ui.chats

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.finderestteam.finderest.R
import androidx.navigation.fragment.NavHostFragment
import com.finderestteam.finderest.MainActivity2
import kotlinx.android.synthetic.main.fragment_chats.view.*


class ChatsFragment : Fragment() {

    private lateinit var homeViewModel: ChatsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(ChatsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chats, container, false)
        root.button13.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.navigation_chat)
        }

        return root
    }
}