package com.example.tollapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.tollapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var welcomeText: TextView
    private lateinit var navigationButton: Button

    private val textToDisplay = "Welcome to the Toll App! Happy Tolling :)"
    private var currentIndex = 0
    private val delayMillis: Long = 100 // Delay between each letter in milliseconds

    private val handler = Handler()

    private val textRunnable = object : Runnable {
        override fun run() {
            if (currentIndex <= textToDisplay.length) {
                welcomeText.text = textToDisplay.substring(0, currentIndex)
                currentIndex++
                handler.postDelayed(this, delayMillis)
            } else {
                // Text fully displayed, show the button
                navigationButton.animate().alpha(1f).duration = 1000 // Fade-in animation for the button
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        welcomeText = findViewById(R.id.welcomeText)
        navigationButton = findViewById(R.id.navigationButton)

        // Start displaying text letter by letter
        handler.postDelayed(textRunnable, delayMillis * 2) // Initial delay before text starts
        navigationButton.alpha = 0f // Make the button invisible initially

        fun onDestroy() {
            super.onDestroy()
            handler.removeCallbacks(textRunnable) // Remove callbacks to avoid memory leaks
        }

        val videoView: VideoView = findViewById(R.id.videoView)
        val videoPath = "android.resource://" + packageName + "/" + R.raw.homebg111
        val videoUri = Uri.parse(videoPath)

        videoView.setVideoURI(videoUri)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        videoView.start()

        navigationButton.setOnClickListener {
            // Navigate to the SignInActivity on button click
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
