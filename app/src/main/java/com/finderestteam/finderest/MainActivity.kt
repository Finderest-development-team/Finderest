package com.finderestteam.finderest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.finderestteam.finderest.ui.registation.RegistrationPart1
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //тут должна будет быть проверна входил ли пользователь в приложение
        if(FirebaseAuth.getInstance().currentUser == null)
        {
            val int2 = Intent(this, RegistrationPart1::class.java)
            startActivity(int2)
            overridePendingTransition(R.anim.transition_in, R.anim.transition_out)
        }else{
            val int = Intent(this, MainActivity2::class.java)
            startActivity(int)
            overridePendingTransition(R.anim.transition_in, R.anim.transition_out)
        }
    }
}