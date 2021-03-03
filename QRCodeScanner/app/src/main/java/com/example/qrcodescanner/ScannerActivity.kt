package com.example.qrcodescanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ScannerActivity : AppCompatActivity() {

    private lateinit var welcomeMsg : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)
        welcomeMsg = findViewById(com.example.qrcodescanner.R.id.welcome_id)

    }
}