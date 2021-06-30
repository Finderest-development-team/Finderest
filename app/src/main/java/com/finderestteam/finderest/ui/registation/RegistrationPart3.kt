package com.finderestteam.finderest.ui.registation

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.finderestteam.finderest.MainActivity
import com.finderestteam.finderest.PersonData
import com.finderestteam.finderest.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import de.hdodenhof.circleimageview.CircleImageView
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
    private var profilePictureUrl:String = ""
    private lateinit var user:PersonData

    @RequiresApi(Build.VERSION_CODES.N)
    fun register(view: View){
        if(checkMail() && checkPass() && checkName() && checkPhoto()){
            signInNewUser(mail, password)
            user = PersonData(null, name, mail, password, null, null)
        }
    }

    private fun checkMail(): Boolean {
        mail = findViewById<EditText>(R.id.editTextTextEmailAddress2).text.toString()
        if(mail == "") {
            Toast.makeText(this, "You have an empty fields", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun checkPass(): Boolean {
        if(findViewById<EditText>(R.id.editTextTextEmailPassword2).text.toString() ==
            findViewById<EditText>(R.id.editTextNumberPassword3).text.toString())
        {
            password = findViewById<EditText>(R.id.editTextTextEmailPassword2).text.toString()
            if(password == "") {
                Toast.makeText(this, "You have an empty fields", Toast.LENGTH_SHORT).show()
                return false
            }
        }else{
            Toast.makeText(this, "You didn't confirm your password", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun checkName(): Boolean {
        name = findViewById<EditText>(R.id.editTextTextPersonName2).text.toString()
        if(name == "") {
            Toast.makeText(this, "You have an empty field", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun checkPhoto(): Boolean{
        return if (filePath != null) {
            true
        }else{
            Toast.makeText(this, "Put your image", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun uploadPhoto(){
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Uploading...")
        progressDialog.show()
        val ref = FirebaseStorage.getInstance().getReference("/images/$mail")
        val uid = FirebaseAuth.getInstance().uid

        ref.putFile(filePath!!)
        .addOnSuccessListener {
            Log.d("PROFILE_PICTURE", profilePictureUrl)
            progressDialog.dismiss()
            Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show()

            ref.downloadUrl.addOnSuccessListener {

                user = PersonData(uid.toString(), name, mail, password, it.toString(), intent.getStringExtra("listOfInterests"))

                FirebaseDatabase.getInstance().getReference("users").child(uid.toString()).setValue(user)
                    .addOnSuccessListener {
                        Toast.makeText(this, "createUserWithEmail:success", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener{ e ->
                        Toast.makeText(this, "Failed " + e.message, Toast.LENGTH_SHORT)
                            .show()

                        Toast.makeText(
                            this,
                            "createUserWithEmail:failure:photoWasNotUploaded",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }

            finish()
        }
        .addOnFailureListener { e ->
            progressDialog.dismiss()
            Toast.makeText(this, "Failed " + e.message, Toast.LENGTH_SHORT)
                .show()

            Toast.makeText(
                this,
                "createUserWithEmail:failure:photoWasNotUploaded",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        }
        .addOnProgressListener { taskSnapshot ->
            val progress =
                100.0 * taskSnapshot.bytesTransferred / taskSnapshot
                    .totalByteCount
            progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
        }

    }

    fun loadPhoto(v: View){
        ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.INTERNET)
            ,1)
    }

    private fun signInNewUser(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                uploadPhoto()
            } else {
                Toast.makeText(
                    this,
                    "createUserWithEmail:failure:userWasNotCreated",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
        }
    }
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

            findViewById<CircleImageView>(R.id.uploadedPhotoRegistration).setImageBitmap(bitmap)
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
                            android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                        && (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                        val intent = Intent()
                        intent.type = "image/*"
                        intent.action = Intent.ACTION_GET_CONTENT
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}
