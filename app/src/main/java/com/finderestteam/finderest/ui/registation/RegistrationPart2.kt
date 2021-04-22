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
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.input_interests)
        val butt = findViewById<Button>(R.id.button)
        val ques = findViewById<TextView>(R.id.Question)
        val bar = findViewById<ProgressBar>(R.id.progressBar2)
        val ans = findViewById<Switch>(R.id.answer)

        val arr = arrayOf(
            "Do u like sport?",
            "Do u want to find love?",
            "Do u like cats?",
            "Lets move on"
        )
        var i = 0
        ques.setText(arr[i])
        ans.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                Log.d("TAG", "Switch-${i.toString()} was activated")
            if (!isChecked)
                Log.d("TAG", "Switch-${i.toString()} was not activated")
        }
        butt.setOnClickListener {
            ans.isChecked = false
            ++i
            if (i == 4) {
                Log.d("TAG", "move to next part")
                val int2 = Intent(this, RegistrationPart3::class.java)
                startActivityForResult(int2, 2)
                overridePendingTransition(R.anim.transition_in, R.anim.transition_out)
            }
            if (i == 3) {
                ans.visibility = View.INVISIBLE
                ques.setText(arr[i])
                bar.setProgress(i, true)
            } else {
                ques.setText(arr[i])
                bar.setProgress(i, true)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null)
            return
        when (resultCode) {
            2 -> {
                val int1 = Intent()
                int1.putExtra("name","data2")
                setResult(1, int1)
                finish()}
            else -> {Log.d("TAG","Smth went wrong")}
        }
    }
}