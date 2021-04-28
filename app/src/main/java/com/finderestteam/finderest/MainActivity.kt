package com.finderestteam.finderest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.finderestteam.finderest.ui.registation.RegistrationPart1
import com.finderestteam.finderest.ui.registation.RegistrationPart2
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //тут должна будет быть проверна входил ли пользователь в приложение
        var logIn = true;
        if(logIn){
            val int = Intent(this, MainActivity2::class.java)
            startActivity(int)
            overridePendingTransition(R.anim.transition_in, R.anim.transition_out)
        }else{
            val int2 = Intent(this, RegistrationPart1::class.java)
            startActivity(int2)
            overridePendingTransition(R.anim.transition_in, R.anim.transition_out)
        }

    }
}
