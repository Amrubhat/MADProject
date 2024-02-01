package com.example.tollapp
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SourcedestActivity : AppCompatActivity() {

    private lateinit var sourceSpinner: Spinner
    private lateinit var destinationSpinner: Spinner
    private lateinit var sourceTextView: TextView
    private lateinit var destinationTextView: TextView
    private lateinit var costTextView: TextView
    private lateinit var nextButton: Button

    // Define the places and associated costs
    private val places = listOf("Tumkur Road", "PES College", "Sompura", "Kengeri", "Hosur Road", "Kanakpura Road", "Bannerghatta Road")
    private val costs = arrayOf(
        intArrayOf(0, 50, 35, 70, 40, 60, 55),  // Cost for traveling from Tumkur Road to other places
        intArrayOf(50, 0, 45, 30, 55, 75, 80),  // Cost for traveling from PES College to other places
        // ... Continue with similar rows for other places
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sourcedest)

        // Initialize UI components
        sourceSpinner = findViewById(R.id.spinner)
        destinationSpinner = findViewById(R.id.spinner2)
        sourceTextView = findViewById(R.id.textView2)
        destinationTextView = findViewById(R.id.textView3)
        costTextView = findViewById(R.id.textView4)
        nextButton = findViewById(R.id.button)

        // Set up spinners with dummy data (replace with your actual data)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, places)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        sourceSpinner.adapter = adapter
        destinationSpinner.adapter = adapter

        // Set up OnClickListener for the "Next" button
        nextButton.setOnClickListener {
            calculateAndDisplayCost()
            navigateToPayActivity()
        }
    }

    private fun calculateAndDisplayCost() {
        val sourceIndex = sourceSpinner.selectedItemPosition
        val destinationIndex = destinationSpinner.selectedItemPosition

        if (sourceIndex >= 0 && destinationIndex >= 0) {
            val cost = costs[sourceIndex][destinationIndex]
            costTextView.text = "Cost: $cost rupees"
        } else {
            costTextView.text = "Invalid selection"
        }
    }

    private fun navigateToPayActivity() {
        val selectedSource = sourceSpinner.selectedItem.toString()
        val selectedDestination = destinationSpinner.selectedItem.toString()

        // Get vehicle number and type from the previous HomeActivity
        val vehicleNumber = intent.getStringExtra("VEHICLE_NUMBER")
        val vehicleType = intent.getStringExtra("VEHICLE_TYPE")

        val intent = Intent(this, PayActivity::class.java).apply {
            putExtra("SOURCE", selectedSource)
            putExtra("DESTINATION", selectedDestination)
            putExtra("VEHICLE_NUMBER", vehicleNumber)
            putExtra("VEHICLE_TYPE", vehicleType)
        }

        startActivity(intent)
    }
}
