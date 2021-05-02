package com.finderestteam.finderest.ui.chats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import com.finderestteam.finderest.R
import kotlinx.android.synthetic.main.fragment_chats.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ChatsFragment : Fragment() {

    private lateinit var homeViewModel: ChatsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(ChatsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_chats, container, false)
    }
}