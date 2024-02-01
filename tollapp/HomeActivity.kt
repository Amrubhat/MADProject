package com.example.tollapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val paymentButton3: Button = findViewById(R.id.button3)
        val paymentButton4: Button = findViewById(R.id.button4)
        val paymentButton5: Button = findViewById(R.id.button5)
        val paymentButton6: Button = findViewById(R.id.button6)
        val paymentButton7: Button = findViewById(R.id.button7)
        val paymentButton8: Button = findViewById(R.id.button8)
        val paymentButton9: Button = findViewById(R.id.button9)

        paymentButton3.setOnClickListener(buttonClickListener)
        paymentButton4.setOnClickListener(buttonClickListener)
        paymentButton5.setOnClickListener(buttonClickListener)
        paymentButton6.setOnClickListener(buttonClickListener)
        paymentButton7.setOnClickListener(buttonClickListener)
        paymentButton8.setOnClickListener(buttonClickListener)
        paymentButton9.setOnClickListener(buttonClickListener)
    }

    private val buttonClickListener = View.OnClickListener { view ->
//        val et1 = findViewById<EditText>(R.id.editTextTextEmailAddress2)
//        val vehicleNumber = et1.text.toString()
        when (view?.id) {
            R.id.button3 -> navigateToPayment("Car/Jeep")
            R.id.button4 -> navigateToPayment("3 Wheeler")
            R.id.button5 -> navigateToPayment("LCV")
            R.id.button6 -> navigateToPayment("Bus")
            R.id.button7 -> navigateToPayment("Truck")
            R.id.button8 -> navigateToPayment("MAV")
            R.id.button9 -> navigateToPayment("2 Wheeler")
        }
    }

    private fun navigateToPayment(vehicleType: String) {
        val et1 = findViewById<EditText>(R.id.editTextTextEmailAddress2)
        val vehicleNumber = et1.text.toString()
        val intent = Intent(this, SourcedestActivity::class.java).apply {
            putExtra("VEHICLE_TYPE", vehicleType)
            putExtra("VEHICLE_NUMBER", vehicleNumber)
        }
        startActivity(intent)
    }
}
