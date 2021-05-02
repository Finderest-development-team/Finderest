package com.finderestteam.finderest.ui.registation

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import com.finderestteam.finderest.MainActivity2
import com.finderestteam.finderest.R

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
            val int = Intent(this, MainActivity2::class.java)
            startActivity(int)
            overridePendingTransition(R.anim.transition_in, R.anim.transition_out)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null)
            return
        when (resultCode) {
            1 -> {
                val arr = data.getStringArrayExtra("result.code.registrationpart2")
                Toast.makeText(this, "End of the registration ur mail:${arr?.get(0)} ur password: ${arr?.get(1)} ur name: ${arr?.get(2)} and ur interests: ${arr?.get(3)},${arr?.get(4)},${arr?.get(5)}", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Log.d("TAG", "Smth went wrong")
            }
        }
    }
}