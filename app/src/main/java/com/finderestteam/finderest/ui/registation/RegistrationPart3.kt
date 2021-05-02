package com.finderestteam.finderest.ui.registation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.finderestteam.finderest.R

class RegistrationPart3 : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.whole_registration_02)
    }
    private var mail:String = ""
    private var password:String = ""
    private var name:String = ""

    @RequiresApi(Build.VERSION_CODES.N)
    fun button1(view: View){
        findViewById<EditText>(R.id.editTextTextEmailAddress2).addTextChangedListener(object :
            TextWatcher {
            override fun afterTextChanged(s: Editable) {
                mail = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
        val bar = findViewById<ProgressBar>(R.id.progressBar5)
        bar.setProgress(1, true)
        val lay1 = findViewById<ConstraintLayout>(R.id.Layout1)
        val lay2 = findViewById<ConstraintLayout>(R.id.Layout2)
        lay1.visibility = View.GONE
        lay2.visibility = View.VISIBLE
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun button2(view: View){
        val bar = findViewById<ProgressBar>(R.id.progressBar5)
        bar.setProgress(2, true)
        findViewById<EditText>(R.id.editTextTextEmailPassword2).addTextChangedListener(object :
            TextWatcher {
            override fun afterTextChanged(s: Editable) {
                password = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
        val lay2 = findViewById<ConstraintLayout>(R.id.Layout2)
        val lay3 = findViewById<ConstraintLayout>(R.id.Layout3)
        lay2.visibility = View.GONE
        lay3.visibility = View.VISIBLE
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun button3(view: View){
        val bar = findViewById<ProgressBar>(R.id.progressBar5)
        bar.setProgress(3, true)
        findViewById<EditText>(R.id.editTextTextPersonName2).addTextChangedListener(object :
            TextWatcher {
            override fun afterTextChanged(s: Editable) {
                name = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
        val lay3 = findViewById<ConstraintLayout>(R.id.Layout3)
        val lay4 = findViewById<ConstraintLayout>(R.id.Layout4)
        lay3.visibility = View.GONE
        lay4.visibility = View.VISIBLE
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun button4(view: View){
        val bar = findViewById<ProgressBar>(R.id.progressBar5)
        bar.setProgress(4, true)
        val mess = Toast.makeText(this, "End of the registration", Toast.LENGTH_SHORT)
        mess.show()
        val int2 = Intent()
        val arr = arrayOf(mail, password, name)
        int2.putExtra("result.code.registrationpart3", arr)
        setResult(2, int2)
        finish()
    }
}