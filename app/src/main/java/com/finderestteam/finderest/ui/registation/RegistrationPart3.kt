package com.finderestteam.finderest.ui.registation

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import com.finderestteam.finderest.R

class RegistrationPart3 : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.whole_registation)
        val buu = findViewById<Button>(R.id.button7)
        buu.setOnClickListener {
            val int2 = Intent()
            int2.putExtra("name","data3")
            setResult(2, int2)
            finish()
        }
    }
}