package com.finderestteam.finderest.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import com.finderestteam.finderest.R
import com.finderestteam.finderest.ui.chats.LatestMessages
import com.finderestteam.finderest.ui.chats.NewMessageActivity
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
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

        val imageView = root.findViewById<CircleImageView>(R.id.circleImageView_profile)
        val nameView  = root.findViewById<TextView>(R.id.textView4)

        Picasso.get().load(LatestMessages.currentUser?.profileImageUrl)
            .into(imageView)
        nameView.text = LatestMessages.currentUser?.userName

        root.about_me_button.setOnClickListener {
            val int = Intent(activity, AboutMe::class.java)
            startActivity(int)
        }

        return root
    }
}