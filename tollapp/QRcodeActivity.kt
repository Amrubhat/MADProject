package com.example.tollapp

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter

class QRcodeActivity : AppCompatActivity() {

    private lateinit var ivQRCode: ImageView
    private lateinit var btnGenerateQRCode: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_qrcode)

        ivQRCode = findViewById(R.id.ivQRcode)
        btnGenerateQRCode = findViewById(R.id.btnGenerateQRcode)
        btnGenerateQRCode.setOnClickListener {
            generateQRCode()
        }
    }

    private fun generateQRCode() {
        val intent = intent;
        val vno = intent.getStringExtra("VEHICLE_NUMBER");
        val vtype = intent.getStringExtra("VEHICLE_TYPE");
        val hours = intent.getLongExtra("HOURS", 0)
        val minutes = intent.getLongExtra("MINUTES", 0)
        val seconds = intent.getLongExtra("SECONDS", 0)
        val paymentDate = intent.getStringExtra("DATE")
        val psource = intent.getStringExtra("SOURCE")
        val pdest = intent.getStringExtra("DESTINATION")

        val data = "Vehicle Number: $vno\nVehicle Type: $vtype\nSource: $psource\nDestination: $pdest\nPayment Date: $paymentDate\nPaid"

//        val data = "Vehicle Number:"+ vno.toString()+"\nVehicle Type: "+vtype.toString()+"\n Payment time"+hours.toString()+" \t"+minutes.toString()+" \t"+seconds.toString()+"    \n Paid";

        val writer = QRCodeWriter()
        try {
            val bitMatrix: BitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            ivQRCode.setImageBitmap(bmp)
        } catch (e: WriterException) {
            e.printStackTrace()
            Toast.makeText(this, "Error generating QR code", Toast.LENGTH_SHORT).show()
        }
    }
}
