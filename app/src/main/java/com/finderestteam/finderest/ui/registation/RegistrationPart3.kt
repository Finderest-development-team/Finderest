package com.finderestteam.finderest.ui.registation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.finderestteam.finderest.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class RegistrationPart3 : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.whole_registration_02)
    }
    private var mail:String = ""
    private var password:String = ""
    private var name:String = ""
    var photo: ImageView? = null

    private fun hasPermission(): Boolean {
        return true
    }

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
        if(findViewById<EditText>(R.id.editTextTextEmailPassword2).text.toString() == findViewById<EditText>(R.id.editTextNumberPassword3).text.toString()) {
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
        }else{
            Toast.makeText(this, "You didn't confirm your password", Toast.LENGTH_SHORT).show()
        }
    }
    fun loadPhoto(view: View){
        val loadIntent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(loadIntent, 1)
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
        val int2 = Intent()
        val arr = arrayOf(mail, password, name)
        int2.putExtra("result.code.registrationpart3", arr)
        setResult(2, int2)
        finish()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //я не понимаю как получить uri фотки которую мы выбрали
        val u = intent.data
        Toast.makeText(this, "imageuRI: $u", Toast.LENGTH_SHORT).show()
        findViewById<ImageButton>(R.id.imageButton2).setImageURI(u)
    }
}

