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
import kotlinx.android.synthetic.main.activity_latest_messages.view.*


class LatestMessages : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_latest_messages, container, false)
        root.newChat.setOnClickListener {
            val int = Intent(getActivity(), NewMessageActivity::class.java)
            startActivity(int)
        }

        return root
    }
}