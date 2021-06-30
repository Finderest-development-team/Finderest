package com.finderestteam.finderest.ui.registation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.finderestteam.finderest.MainActivity2
import com.finderestteam.finderest.PersonData
import com.finderestteam.finderest.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class RegistrationPart1 : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val butt = findViewById<TextView>(R.id.registrationButton)
        butt.setOnClickListener {
            val int = Intent(this, RegistrationPart2::class.java)
            startActivity(int)
            overridePendingTransition(R.anim.transition_in, R.anim.transition_out)
        }
        val butt2 = findViewById<Button>(R.id.enterButton)
        butt2.setOnClickListener {
            if(findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString() == "" && findViewById<EditText>(
                    R.id.editTextTextPassword
                ).text.toString() == "") {
                Toast.makeText(this, "You have an empty fields", Toast.LENGTH_SHORT).show()
            }else{
                signInExistingUser(
                    findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString(),
                    findViewById<EditText>(R.id.editTextTextPassword).text.toString()
                )
            }
        }
    }

    private fun signInExistingUser(email: String, password: String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(
            this
        ) {
            if (it.isSuccessful) {
                val user = FirebaseAuth.getInstance().currentUser
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.transition_in, R.anim.transition_out)
                Toast.makeText(this, "signInWithEmail:success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "signInWithEmail:failure: ${it.exception.toString()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}
