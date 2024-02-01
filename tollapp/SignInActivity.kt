package com.example.tollapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val videoView1: VideoView = findViewById(R.id.videoView)
        val videoPath1 = "android.resource://" + packageName + "/" + R.raw.background_video1
        val videoUri1 = Uri.parse(videoPath1)

        videoView1.setVideoURI(videoUri1)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView1)
        videoView1.setMediaController(mediaController)

        videoView1.start()

        val signInButton: Button = findViewById(R.id.button)
        val emailEditText: EditText = findViewById(R.id.editTextTextEmailAddress)

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()

            if (isValidEmail(email)) {
                if (email.endsWith("@gmail.com")) {
                    // Navigate to the HomeActivity when the email is valid
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Please enter a Gmail account", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
            }
        }

        val loginButton: Button = findViewById(R.id.button2)
        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val pattern = Regex(emailPattern)
        return pattern.matches(email)
    }
}
