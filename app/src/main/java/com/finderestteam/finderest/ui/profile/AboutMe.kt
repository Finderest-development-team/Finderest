package com.finderestteam.finderest.ui.profile

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.finderestteam.finderest.PersonData
import com.finderestteam.finderest.R
import com.finderestteam.finderest.ui.chats.LatestMessages
import com.finderestteam.finderest.ui.registation.RegistrationPart2
import com.finderestteam.finderest.ui.registation.RegistrationPart3
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class AboutMe : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var profilePictureUrl:String = ""
    private val currentUser = LatestMessages.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)
        supportActionBar?.title = "About me"

        val imageView = findViewById<ImageButton>(R.id.imageButton)
        val nameView = findViewById<TextView>(R.id.textView8)
        val mailView = findViewById<TextView>(R.id.textView)

        Picasso.get().load(currentUser?.profileImageUrl).into(imageView)
        nameView.text = currentUser?.userName
        mailView.text = currentUser?.userMail

        findViewById<Button>(R.id.change_interests_about_me).setOnClickListener {
            val int = Intent(this, RegistrationPart2::class.java)
            int.putExtra("changeExistentInformation", true)
            startActivity(int)
        }

        findViewById<Button>(R.id.change_profile_picture_about_me).setOnClickListener {
            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.INTERNET)
                ,1)
        }
    }

    private fun uploadPhoto(){
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Uploading...")
        progressDialog.show()
        val ref = FirebaseStorage.getInstance().getReference("/images/${currentUser?.userMail}")

        ref.putFile(filePath!!)
            .addOnSuccessListener {
                Log.d("PROFILE_PICTURE", profilePictureUrl)
                progressDialog.dismiss()
                Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show()

                ref.downloadUrl.addOnSuccessListener {

                    currentUser?.profileImageUrl = it.toString()

                    FirebaseDatabase.getInstance().getReference("users").child("${currentUser?.uid}").setValue(currentUser)
                        .addOnSuccessListener {
                            Toast.makeText(this, "changedProfilePicture:success", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener{ e ->
                            Toast.makeText(this, "Failed " + e.message, Toast.LENGTH_SHORT)
                                .show()

                            Toast.makeText(
                                this,
                                "changedProfilePicture:photoWasNotUploaded",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }

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

            }
            .addOnProgressListener { taskSnapshot ->
                val progress =
                    100.0 * taskSnapshot.bytesTransferred / taskSnapshot
                        .totalByteCount
                progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
            }

    }


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null
        ) {
            filePath = data.data

            Picasso.get().load(data.data).into(findViewById<ImageButton>(R.id.imageButton))

            uploadPhoto()
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
