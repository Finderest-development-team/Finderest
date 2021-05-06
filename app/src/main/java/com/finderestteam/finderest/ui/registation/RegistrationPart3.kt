package com.finderestteam.finderest.ui.registation

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.finderestteam.finderest.R
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
    var photo: String = ""

    @RequiresApi(Build.VERSION_CODES.N)
    fun button1(view: View){
        mail = findViewById<EditText>(R.id.editTextTextEmailAddress2).text.toString()
        if(mail != "") {
            val bar = findViewById<ProgressBar>(R.id.progressBar5)
            bar.setProgress(1, true)
            val lay1 = findViewById<ConstraintLayout>(R.id.Layout1)
            val lay2 = findViewById<ConstraintLayout>(R.id.Layout2)
            lay1.visibility = View.GONE
            lay2.visibility = View.VISIBLE
        }else{
            Toast.makeText(this, "You have an empty fields", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun button2(view: View){
        if(findViewById<EditText>(R.id.editTextTextEmailPassword2).text.toString() == findViewById<EditText>(
                R.id.editTextNumberPassword3
            ).text.toString()) {
            password = findViewById<EditText>(R.id.editTextTextEmailPassword2).text.toString()
            if(password != "") {
                val bar = findViewById<ProgressBar>(R.id.progressBar5)
                bar.setProgress(2, true)
                val lay2 = findViewById<ConstraintLayout>(R.id.Layout2)
                val lay3 = findViewById<ConstraintLayout>(R.id.Layout3)
                lay2.visibility = View.GONE
                lay3.visibility = View.VISIBLE
            }else{
                Toast.makeText(this, "You have an empty fields", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "You didn't confirm your password", Toast.LENGTH_SHORT).show()
        }
    }
    fun loadPhoto(view: View){
        val loadIntent =
            Intent(Intent.ACTION_GET_CONTENT)
        loadIntent.type = "*/*"
        startActivityForResult(loadIntent, 1)
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun button3(view: View){
        name = findViewById<EditText>(R.id.editTextTextPersonName2).text.toString()
        if(name != "") {
            val bar = findViewById<ProgressBar>(R.id.progressBar5)
            bar.setProgress(3, true)
            val lay3 = findViewById<ConstraintLayout>(R.id.Layout3)
            val lay4 = findViewById<ConstraintLayout>(R.id.Layout4)
            lay3.visibility = View.GONE
            lay4.visibility = View.VISIBLE
        }else{
            Toast.makeText(this, "You have an empty field", Toast.LENGTH_SHORT).show()
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun button4(view: View){
        if(photo != ""){
            val bar = findViewById<ProgressBar>(R.id.progressBar5)
            bar.setProgress(4, true)
            signInNewUser(mail,password)
            findViewById<ConstraintLayout>(R.id.Layout4).visibility = View.GONE
            findViewById<ConstraintLayout>(R.id.Layout5).visibility = View.VISIBLE
        }else{
            Toast.makeText(this, "Pls put your photo", Toast.LENGTH_SHORT).show()
        }
    }
    fun button5(view: View){
        val int2 = Intent()
        val arr = arrayOf(mail, password, name, photo)
        int2.putExtra("result.code.registrationpart3", arr)
        setResult(2, int2)
        finish()
    }
    private fun signInNewUser(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(this
        ) {
            if (it.isSuccessful) {
                val user = FirebaseAuth.getInstance().currentUser
                Toast.makeText(this, "createUserWithEmail:success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "createUserWithEmail:failure: ${it.exception.toString()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (data.data != null) {
                photo = data.data.toString()
                findViewById<ImageButton>(R.id.imageButton2).setImageURI(data.data)
            }
        }
    }
}
