package com.example.qrcodescanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.integration.android.IntentIntegrator
import java.text.SimpleDateFormat
import java.util.*

class ScannerActivity : AppCompatActivity() {

    //Declaring variables
    private lateinit var scanButton : Button
    private var qrScanIntegrator: IntentIntegrator? = null
    private var user:String? = ""
    private var timeNow :String? =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        //Receiving username from main activity
        user= intent.getStringExtra("Username")

        //Initializing variables
        initializer()

        //Initiating scan
        scanButton.setOnClickListener {
            performAction()
        }
        qrScanIntegrator = IntentIntegrator(this)
        qrScanIntegrator?.setOrientationLocked(false)

    }

    //Initializing variables
    private fun initializer(){
        scanButton = findViewById(R.id.scanButton_id)
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")
        timeNow = simpleDateFormat.format(Date())
    }

    //Initiating scan.
    private fun performAction() {
        qrScanIntegrator?.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {

            // If QRCode has no data.
            if (result.contents == null) {
                Toast.makeText(this, "Invalid QR code!", Toast.LENGTH_SHORT).show()
            }
            else {

                //On successful scan, compare data with secret CODE to validate QR code.
                var CODE :String = result.contents.toString();

                //If result data matches with CODE send username to DB to mark attendance
                if(CODE == "abc.com") {
                    val database = FirebaseDatabase.getInstance()
                    val myRef = database.getReference("Participants")
                    Toast.makeText(this, "Attendance marked successfully.", Toast.LENGTH_SHORT).show()

                    //Setting firebase unique key for Hashmap list
                    val key: String? = myRef.push().getKey()
                    if (key != null) {

                        //Sending username with timestamp to DB.
                        myRef.child(key).setValue(user,  timeNow)
                    }
                    Toast.makeText(this, "Attendance marked successfully.", Toast.LENGTH_SHORT).show()
                }
                //If data does not match, show failure message
                else {
                   Toast.makeText(this, "Invalid QR code!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}