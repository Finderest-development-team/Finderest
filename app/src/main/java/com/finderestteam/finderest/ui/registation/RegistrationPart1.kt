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

class RegistrationPart1 : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.input_interests)
        val butt = findViewById<Button>(R.id.button)
        val ques = findViewById<TextView>(R.id.Question)
        val bar = findViewById<ProgressBar>(R.id.progressBar2)
        val ans = findViewById<Switch>(R.id.answer)

        val arr = arrayOf("Do u like sport?","Do u want to find love?","Do u like cats?", "Lets move on")
        var i = 0
        ques.setText(arr[i])
        ans.setOnClickListener {
            Log.d("TAG", "Switch-${i.toString()} was activated")
        }
        butt.setOnClickListener {
            if(i < 3) {
                ans.isChecked = false
                ++i
                ques.setText(arr[i])
            }
            bar.setProgress(i, true)
            if(i == 3) {
                ans.visibility = View.INVISIBLE
            }
            Log.d("TAG", "move to next registration part")
        }
    }
}