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
    private var arr = arrayOf("false", "false", "false", "false", "false", "false", "false", "false", "false")
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.input_interests)
        findViewById<TextView>(R.id.Question).setText("Are u interested in sport?")
        findViewById<Switch>(R.id.answer).setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Log.d("TAG", "Switch was activated")
                when{
                    findViewById<Button>(R.id.button).visibility == View.VISIBLE ->{arr[0]="true"}
                    findViewById<Button>(R.id.button2).visibility == View.VISIBLE ->{arr[1]="true"}
                    findViewById<Button>(R.id.button3).visibility == View.VISIBLE ->{arr[2]="true"}
                    findViewById<Button>(R.id.button4).visibility == View.VISIBLE ->{arr[3]="true"}
                    findViewById<Button>(R.id.button5).visibility == View.VISIBLE ->{arr[4]="true"}
                    findViewById<Button>(R.id.button6).visibility == View.VISIBLE ->{arr[5]="true"}
                    findViewById<Button>(R.id.button7).visibility == View.VISIBLE ->{arr[6]="true"}
                    findViewById<Button>(R.id.button8).visibility == View.VISIBLE ->{arr[7]="true"}
                    findViewById<Button>(R.id.button9).visibility == View.VISIBLE ->{arr[8]="true"}
                }
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun que1(view:View){
        findViewById<TextView>(R.id.Question).setText("Do you like sport?")
        findViewById<ProgressBar>(R.id.progressBar2).setProgress(1, true)
        findViewById<Button>(R.id.button).visibility = View.GONE
        findViewById<Button>(R.id.button2).visibility = View.VISIBLE
        findViewById<Switch>(R.id.answer).isChecked = false
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun que2(view:View){
        findViewById<TextView>(R.id.Question).setText("Would u like to talk about technologies?")
        findViewById<ProgressBar>(R.id.progressBar2).setProgress(2, true)
        findViewById<Button>(R.id.button2).visibility = View.GONE
        findViewById<Button>(R.id.button3).visibility = View.VISIBLE
        findViewById<Switch>(R.id.answer).isChecked = false
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun que3(view:View){
        findViewById<TextView>(R.id.Question).setText("Would like to learn something new about animals?")
        findViewById<ProgressBar>(R.id.progressBar2).setProgress(3, true)
        findViewById<Button>(R.id.button3).visibility = View.GONE
        findViewById<Button>(R.id.button4).visibility = View.VISIBLE
        findViewById<Switch>(R.id.answer).isChecked = false
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun que4(view:View){
        findViewById<TextView>(R.id.Question).setText("Are you a gamer?")
        findViewById<ProgressBar>(R.id.progressBar2).setProgress(4, true)
        findViewById<Button>(R.id.button4).visibility = View.GONE
        findViewById<Button>(R.id.button5).visibility = View.VISIBLE
        findViewById<Switch>(R.id.answer).isChecked = false
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun que5(view:View){
        findViewById<TextView>(R.id.Question).setText("Do u want to get some new knowledge?")
        findViewById<ProgressBar>(R.id.progressBar2).setProgress(5, true)
        findViewById<Button>(R.id.button5).visibility = View.GONE
        findViewById<Button>(R.id.button6).visibility = View.VISIBLE
        findViewById<Switch>(R.id.answer).isChecked = false
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun que6(view:View){
        findViewById<TextView>(R.id.Question).setText("Do you like parties?")
        findViewById<ProgressBar>(R.id.progressBar2).setProgress(6, true)
        findViewById<Button>(R.id.button6).visibility = View.GONE
        findViewById<Button>(R.id.button7).visibility = View.VISIBLE
        findViewById<Switch>(R.id.answer).isChecked = false
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun que7(view:View){
        findViewById<TextView>(R.id.Question).setText("Do you enjoy traveling?")
        findViewById<ProgressBar>(R.id.progressBar2).setProgress(7, true)
        findViewById<Button>(R.id.button7).visibility = View.GONE
        findViewById<Button>(R.id.button8).visibility = View.VISIBLE
        findViewById<Switch>(R.id.answer).isChecked = false
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun que8(view:View){
        findViewById<TextView>(R.id.Question).setText("Do you like going to exhibitions and art in general")
        findViewById<ProgressBar>(R.id.progressBar2).setProgress(8, true)
        findViewById<Button>(R.id.button8).visibility = View.GONE
        findViewById<Button>(R.id.button9).visibility = View.VISIBLE
        findViewById<Switch>(R.id.answer).isChecked = false
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun que9(view:View){
        findViewById<TextView>(R.id.Question).setText("Do you want to find friends for walks?")
        findViewById<ProgressBar>(R.id.progressBar2).setProgress(9, true)
        findViewById<Button>(R.id.button9).visibility = View.GONE
        findViewById<Button>(R.id.button10).visibility = View.VISIBLE
        findViewById<Switch>(R.id.answer).isChecked = false
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun que10(view:View){
        findViewById<TextView>(R.id.Question).setText("Lets move on")
        findViewById<ProgressBar>(R.id.progressBar2).setProgress(10, true)
        findViewById<Button>(R.id.button10).visibility = View.GONE
        findViewById<Button>(R.id.MoveOnBtn).visibility = View.VISIBLE
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
                val data1 = data.getStringArrayExtra("result.code.registrationpart3")?.plus(arr)
                if(data1 == null){
                    Toast.makeText(this, "Item in registration is missing in 0.2", Toast.LENGTH_SHORT).show()
                }
                val int1 = Intent()
                int1.putExtra("result.code.registrationpart2",data1)
                setResult(1, int1)
                finish()}
            else -> {Log.d("TAG","Smth went wrong")}
        }
    }
}