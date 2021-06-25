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

            val int2 = Intent(this, RegistrationPart3::class.java)
            startActivityForResult(int2, 2)
            overridePendingTransition(R.anim.transition_in, R.anim.transition_out)
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

    private fun checkForNulls(array: Array<String?>): Boolean {
        for(i in array){
            if(i == null)
                return true
        }
        return false
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            1 -> {
                if(data != null) {
                    val data1 =
                        data.getStringArrayExtra("result.code.registration.part3")?.plus(answers)
                    if (data1?.let { checkForNulls(it) } == false) {
                        val int1 = Intent()
                        int1.putExtra("result.code.registration.part2", data1)
                        setResult(0, int1)
                        finish()
                    } else {
                        val int1 = Intent()
                        setResult(-3, int1)
                        finish()
                    }
                }else{
                    val int1 = Intent()
                    setResult(-4, int1)
                    finish()
                }
            }
            -1 ->{
                val int1 = Intent()
                setResult(-5, int1)
                finish()
            }
            -2 -> {
                val int1 = Intent()
                setResult(-6, int1)
                finish()
            }
        }
    }
}