package com.example.qrcodescanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.integration.android.IntentIntegrator
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


class ScannerActivity : AppCompatActivity() {

    private lateinit var scanButton : Button
    private var qrScanIntegrator: IntentIntegrator? = null

    private var user:String? = ""
    private var timeNow :String? =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)
        user= intent.getStringExtra("Username")

        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")
        timeNow = simpleDateFormat.format(Date())

        initializer()
        scanButton.setOnClickListener {
            performAction()
        }

        qrScanIntegrator = IntentIntegrator(this)
        qrScanIntegrator?.setOrientationLocked(false)

    }


    private fun initializer(){
        scanButton = findViewById(R.id.scanButton_id)
    }

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
            else
            {
                // If QRCode contains data.
                   /*
                    try {
                    // Converting the data to json format
                    val obj = JSONObject(result.contents)

                    // Show values in UI.
                   txtName?.text = obj.getString("name")
                    Toast.makeText(this, "YESSSSS!!", Toast.LENGTH_SHORT).show()
                    // txtSiteName?.text = obj.getString("site_name")

                } catch (e: JSONException) {
                    e.printStackTrace()

                    // Data not in the expected format. So, whole object as toast message.
                    Toast.makeText(this, result.contents, Toast.LENGTH_LONG).show()
                }


                    */

               var CODE :String = result.contents.toString();
               if(CODE == "abc.com")
               {
                   val database = FirebaseDatabase.getInstance()
                   val myRef = database.getReference("Participants")

                   //myRef.setValue(user)
                   Toast.makeText(this, "Attendance marked successfully.", Toast.LENGTH_SHORT).show()


                   //Setting firebase unique key for Hashmap list
                   val key: String? = myRef.push().getKey()

                   if (key != null) {
                       myRef.child(key).setValue(user,  timeNow)
                   }
                   Toast.makeText(this, "Attendance marked successfully.", Toast.LENGTH_SHORT).show()

               }
               else
               {
                   Toast.makeText(this, "Invalid QR code!", Toast.LENGTH_SHORT).show()
               }

            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}