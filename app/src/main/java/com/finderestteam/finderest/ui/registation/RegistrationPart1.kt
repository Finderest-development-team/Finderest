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
            startActivityForResult(int, 1)
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
            0 -> {
                if (data != null) {
                    val arr = data.getStringArrayExtra("result.code.registration.part2")
                    if (arr?.let { checkForNulls(it) } == false) {
                        FirebaseDatabase.getInstance().getReference("users")
                            .push()
                            .setValue(
                                PersonData(arr[2], arr[0], arr[1], arrayOf(
                                    arr[3],
                                    arr[4],
                                    arr[5],
                                    arr[6],
                                    arr[7],
                                    arr[8],
                                    arr[9],
                                    arr[10],
                                    arr[11],
                                    arr[12]
                                ))
                            )
                    } else {
                        Toast.makeText(this, "data is null", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "data is null", Toast.LENGTH_SHORT).show()
                }
            }
            -3 -> {
                Toast.makeText(this, "Register again", Toast.LENGTH_SHORT).show()
            }
            -4 -> {
                Toast.makeText(this, "Register again", Toast.LENGTH_SHORT).show()
            }
            -5 -> {
                Toast.makeText(this, "Register again", Toast.LENGTH_SHORT).show()
            }
            -6 -> {
                Toast.makeText(this, "Register again", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
