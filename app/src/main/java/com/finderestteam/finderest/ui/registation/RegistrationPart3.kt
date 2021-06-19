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

    private var mail:String = "mail"
    private var password:String = "password"
    private var name:String = "name"
    var photo: String = "photo_uri"

    @RequiresApi(Build.VERSION_CODES.N)
    fun register(view: View){

        mail = findViewById<EditText>(R.id.editTextTextEmailAddress2).text.toString()
        if(mail == "") {
            Toast.makeText(this, "You have an empty fields", Toast.LENGTH_SHORT).show()
            return
        }

        if(findViewById<EditText>(R.id.editTextTextEmailPassword2).text.toString() ==
            findViewById<EditText>(R.id.editTextNumberPassword3).text.toString())
        {
            password = findViewById<EditText>(R.id.editTextTextEmailPassword2).text.toString()
            if(password == "") {
                Toast.makeText(this, "You have an empty fields", Toast.LENGTH_SHORT).show()
                return
            }
        }else{
            Toast.makeText(this, "You didn't confirm your password", Toast.LENGTH_SHORT).show()
            return
        }

        if(photo != "photo_uri"){
            signInNewUser(mail,password)
        }else{
            Toast.makeText(this, "Pls put your photo", Toast.LENGTH_SHORT).show()
            return
        }

        name = findViewById<EditText>(R.id.editTextTextPersonName2).text.toString()
        if(name == "") {
            Toast.makeText(this, "You have an empty field", Toast.LENGTH_SHORT).show()
            return
        }

        val int2 = Intent()
        val arr = arrayOf(mail, password, name, photo)
        int2.putExtra("result.code.registration.part3", arr)
        setResult(2, int2)
        finish()
    }

    fun loadPhoto(view: View){
        val loadIntent =
            Intent(Intent.ACTION_GET_CONTENT)
        loadIntent.type = "*/*"
        startActivityForResult(loadIntent, 1)
    }

    private fun signInNewUser(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
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
