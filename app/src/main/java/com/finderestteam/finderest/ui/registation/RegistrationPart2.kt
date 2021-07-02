package com.finderestteam.finderest.ui.registation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.finderestteam.finderest.R
import com.finderestteam.finderest.ui.chats.LatestMessages
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistrationPart2 : AppCompatActivity() {
    private var answers = arrayOf("false", "false", "false", "false", "false", "false", "false", "false", "false", "false")
    private var questions = arrayOf(
        "Are you interested in sports?",
        "Would u like to talk about technologies?",
        "Would like to learn something new about animals?",
        "Are you a gamer?",
        "Do u want to get some new knowledge?",
        "Do you like parties?",
        "Do you enjoy traveling?",
        "Do you like going to exhibitions and art in general?",
        "Do you want to find friends for walks?",
        "Do u like reading",
        "Lets move on"
    )

    private var questionNumber = 0

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.input_interests)
        findViewById<TextView>(R.id.Question).setText(questions[questionNumber])
        findViewById<Switch>(R.id.answer).setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Log.d("TAG", "Switch was activated")
                answers[questionNumber] = "true"
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun askQuestion(view: View){
        if (questionNumber <= 10)
        {
            questionNumber += 1
        }

        if (questionNumber == 11)
        {
            val interests = getRidOfUninteresting(answers)

            val change = intent.getBooleanExtra("changeExistentInformation", false)

            if (!change) {
                val int2 = Intent(this, RegistrationPart3::class.java)
                int2.putExtra("listOfInterests", interests)
                startActivity(int2)
                overridePendingTransition(R.anim.transition_in, R.anim.transition_out)

            } else {
                val user = LatestMessages.currentUser
                val ref = FirebaseDatabase.getInstance().getReference("/users").child("${user?.uid}")

                user?.userListOfInterests = interests
                ref.setValue(user)
            }
            finish()

        }
        else
        {
            findViewById<TextView>(R.id.Question).setText(questions[questionNumber])
            findViewById<ProgressBar>(R.id.progressBar2).setProgress(questionNumber, true)
            findViewById<Switch>(R.id.answer).isChecked = false

            if (questionNumber == 10)
            {
                findViewById<Switch>(R.id.answer).visibility = View.INVISIBLE
            }
        }
    }

    private fun getRidOfUninteresting(_userListOfInterests: Array<String>): String {
        val arr = mutableListOf( "Sport", "Technologies", "Animals", "Gamer", "Education", "Parties", "Travelling", "Art", "Walking", "Books")
        var str = ""

        if(_userListOfInterests[0] in arr)
        {
            println("in")
            for ((i, v) in _userListOfInterests.withIndex()){
                str += "${_userListOfInterests[i]} "
            }
            return str
        }

        for ((i, v) in _userListOfInterests.withIndex()){
            if(v.toBoolean())
                str += "${arr[i]} "
        }
        return str
    }

}