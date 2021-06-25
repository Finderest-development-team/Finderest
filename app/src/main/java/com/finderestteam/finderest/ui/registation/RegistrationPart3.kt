package com.finderestteam.finderest.ui.registation

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.finderestteam.finderest.MainActivity
import com.finderestteam.finderest.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.util.*
import java.util.jar.Manifest


class RegistrationPart3 : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.whole_registration_02)
    }
    private var filePath: Uri? = null
    private val PICK_IMAGE_REQUEST = 71

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

        /*if(photo != "photo_uri"){
            signInNewUser(mail,password)
        }else{
            Toast.makeText(this, "Pls put your photo", Toast.LENGTH_SHORT).show()
            return
        }*/
        if (filePath != null) {
            /*val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.show()*/
            FirebaseStorage.getInstance().getReference("images").child("images/$mail")
            .putFile(filePath!!)
            /*.addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this, "Failed " + e.message, Toast.LENGTH_SHORT)
                    .show()
            }
            .addOnProgressListener { taskSnapshot ->
                val progress =
                    100.0 * taskSnapshot.bytesTransferred / taskSnapshot
                        .totalByteCount
                progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
            }*/
        }else{
            Toast.makeText(this, "Put your image", Toast.LENGTH_SHORT).show()
            return
        }
        name = findViewById<EditText>(R.id.editTextTextPersonName2).text.toString()
        if(name == "") {
            Toast.makeText(this, "You have an empty field", Toast.LENGTH_SHORT).show()
            return
        }
        signInNewUser(mail, password)
        val int2 = Intent()
        val arr = arrayOf(mail, password, name, photo)
        int2.putExtra("result.code.registration.part3", arr)
        setResult(2, int2)
        finish()
    }

    //fun loadPhoto(view: View){
      //  val loadIntent =
        //    Intent(Intent.ACTION_GET_CONTENT)
        //loadIntent.type = "*/*"
        //startActivityForResult(loadIntent, 1)
    //}

    fun loadPhoto(v: View){
        //ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION.toString()),1)
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
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
    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (data.data != null) {
                photo = data.data.toString()
                findViewById<ImageButton>(R.id.imageButton2).setImageURI(data.data)
            }
        }
    }*/
    protected override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ): Unit {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null
        ) {
            filePath = data.data
            val bitmap =
                MediaStore.Images.Media.getBitmap(contentResolver, filePath)
            findViewById<ImageButton>(R.id.imageButton2).setImageBitmap(bitmap)
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                                PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}
