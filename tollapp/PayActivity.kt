package com.example.tollapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class PayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        val paymentModeSpinner: Spinner = findViewById(R.id.paymentModeSpinner)
        val paymentDetailsLayout: ConstraintLayout = findViewById(R.id.paymentDetailsLayout)

        // Define payment modes in an array resource
        val paymentModes = resources.getStringArray(R.array.payment_modes)

        // Create an adapter for the spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, paymentModes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        paymentModeSpinner.adapter = adapter

        // Spinner item selection listener
        paymentModeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedMode = paymentModes[position]

                // Show/hide payment details based on the selected mode
                if (selectedMode == "Credit Card" || selectedMode == "Debit Card" || selectedMode == "UPI" || selectedMode == "Other Payment Method") {
                    // Show views for entering payment details
                    paymentDetailsLayout.visibility = View.VISIBLE
                } else {
                    // Hide payment details layout for modes not requiring additional details
                    paymentDetailsLayout.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case where nothing is selected (if needed)
            }
        }

        // Handle Pay button click (Add payment processing logic here)
        val payButton: Button = findViewById(R.id.payButton)
        payButton.setOnClickListener {
            // Retrieve data from HomeActivity
            val vehicleNumber = intent.getStringExtra("VEHICLE_NUMBER")
            val vehicleType = intent.getStringExtra("VEHICLE_TYPE")
            val sour = intent.getStringExtra("SOURCE")
            val dest = intent.getStringExtra("DESTINATION")
            val calendar = Calendar.getInstance()
            val currentTimeMillis = calendar.timeInMillis
            val formattedDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(calendar.time)

            // Convert milliseconds to seconds
            val seconds = currentTimeMillis / 1000

            // Calculate hours, minutes, and remaining seconds
            val hours = seconds / 3600
            val minutes = (seconds % 3600) / 60
            val remainingSeconds = seconds % 60


            // For now, let's assume payment is successful, and navigate to QRcodeActivity
            val intent = Intent(this, QRcodeActivity::class.java).apply {
                putExtra("VEHICLE_NUMBER", vehicleNumber)
                putExtra("VEHICLE_TYPE", vehicleType)
                putExtra("SOURCE",sour)
                putExtra("DESTINATION",dest)
                putExtra("HOURS", hours)
                putExtra("MINUTES", minutes)
                putExtra("SECONDS", remainingSeconds)
                putExtra("DATE", formattedDate)
            }
            startActivity(intent)
        }
    }
}

