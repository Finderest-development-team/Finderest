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
        findViewById<TextView>(R.id.Question).setText("Do u like sport?")
        findViewById<Switch>(R.id.answer).setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                Log.d("TAG", "Switch was activated")
            if (!isChecked) {
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun que1(view:View){
        findViewById<TextView>(R.id.Question).setText("Do u want to find love?")
        findViewById<ProgressBar>(R.id.progressBar2).setProgress(1, true)
        findViewById<Button>(R.id.button).visibility = View.GONE
        findViewById<Button>(R.id.button2).visibility = View.VISIBLE
        findViewById<Switch>(R.id.answer).isChecked = false
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun que2(view:View){
        findViewById<TextView>(R.id.Question).setText("Do u like cats?")
        findViewById<ProgressBar>(R.id.progressBar2).setProgress(2, true)
        findViewById<Button>(R.id.button2).visibility = View.GONE
        findViewById<Button>(R.id.button3).visibility = View.VISIBLE
        findViewById<Switch>(R.id.answer).isChecked = false
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun que3(view:View){
        findViewById<TextView>(R.id.Question).setText("Lets move on")
        findViewById<ProgressBar>(R.id.progressBar2).setProgress(3, true)
        findViewById<Button>(R.id.button3).visibility = View.GONE
        findViewById<Button>(R.id.button4).visibility = View.VISIBLE
        findViewById<Switch>(R.id.answer).visibility = View.INVISIBLE
    }
    fun goNext(view:View){
        val int2 = Intent(this, RegistrationPart3::class.java)
        startActivityForResult(int2, 2)
        overridePendingTransition(R.anim.transition_in, R.anim.transition_out)
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