package com.example.tollapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.VideoView
import android.widget.MediaController
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val videoView2: VideoView = findViewById(R.id.videoView)
        val videoPath2 = "android.resource://" + packageName + "/" + R.raw.loginbg1111
        val videoUri2 = Uri.parse(videoPath2)

        videoView2.setVideoURI(videoUri2)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView2)
        videoView2.setMediaController(mediaController)

        videoView2.start()

        val loginButton: Button = findViewById(R.id.button3)
        val emailEditText: EditText = findViewById(R.id.editTextTextEmailAddress)
        loginButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        loginButton.setOnClickListener {
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
    }
        private fun isValidEmail(email: String): Boolean {
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            val pattern = Regex(emailPattern)
            return pattern.matches(email)
        }
}

