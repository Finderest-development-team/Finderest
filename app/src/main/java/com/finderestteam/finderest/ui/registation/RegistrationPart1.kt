package com.finderestteam.finderest.ui.registation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.finderestteam.finderest.MainActivity2
import com.finderestteam.finderest.PersonData
import com.finderestteam.finderest.R
import com.google.firebase.auth.FirebaseAuth

class RegistrationPart1 : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val butt = findViewById<Button>(R.id.registrationButton)
        butt.setOnClickListener {
            val int = Intent(this, RegistrationPart2::class.java)
            startActivityForResult(int, 1)
            overridePendingTransition(R.anim.transition_in, R.anim.transition_out)
        }
        val butt2 = findViewById<Button>(R.id.enterButton)
        butt2.setOnClickListener {
            if(findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString() == "" && findViewById<EditText>(R.id.editTextTextPassword).text.toString() == "") {
                Toast.makeText(this, "You have an empty fields", Toast.LENGTH_SHORT).show()
            }else{
                signInExistingUser(
                    findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString(),
                    findViewById<EditText>(R.id.editTextTextPassword).text.toString()
                )
            }
        }
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

    private fun signInExistingUser(email: String, password: String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(this
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null)
            return
        when (resultCode) {
            1 -> {
                val arr = data.getStringArrayExtra("result.code.registrationpart2")
                val name = arr?.get(2)
                val mail1 = arr?.get(0)
                val password1 = arr?.get(1)
                val interests = arrayOf(
                    arr?.get(3),
                    arr?.get(4),
                    arr?.get(5),
                    arr?.get(6),
                    arr?.get(7),
                    arr?.get(8),
                    arr?.get(9),
                    arr?.get(10),
                    arr?.get(11)
                )
                if(name==null || mail1==null || password1==null){
                    Toast.makeText(this, "Item in registration is missing in 0.1", Toast.LENGTH_SHORT).show()
                }else{
                    /*if(interests == null){
                        Toast.makeText(this, "Item in input interests is missing", Toast.LENGTH_SHORT).show()
                    }else{
                        val person = PersonData(name, mail1, password1, interests)
                    }*/
                    signInNewUser(mail1,password1)
                }
            }
            else -> {
                Log.d("TAG", "Smth went wrong")
            }
        }
    }
}