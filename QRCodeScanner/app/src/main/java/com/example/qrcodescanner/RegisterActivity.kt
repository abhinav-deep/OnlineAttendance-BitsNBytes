package com.example.qrcodescanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {

    //Declaring variables
    private val forTAG  = "CreateAccountActivity"
    private var forName: String? = null
    private var forRoll: String? = null
    private var forPassword: String? = null
    private var forEmail: String? = null
    private lateinit var name: EditText
    private lateinit var roll: EditText
    private lateinit var pass: EditText
    private lateinit var email: EditText
    private lateinit var register: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Initialize variables
        initialize()
    }

    //Initializing variables
    private fun initialize() {
        name = findViewById(R.id.rgName_id)
        roll = findViewById(R.id.rgRoll_id)
        pass = findViewById(R.id.rgPassword_id)
        email = findViewById(R.id.rgEmail_id)
        register = findViewById(R.id.rgButton_id)
        auth = Firebase.auth

        //Register new user
        register.setOnClickListener {
            val currentUser = auth.currentUser
            //Check if user already exists
            /*
            if (currentUser != null) {
                Toast.makeText(this, "User already exists!", Toast.LENGTH_SHORT).show();
            }
            else
                createNewAccount()
            */
            createNewAccount()
        }
    }

    //Create new account in DB
    private fun createNewAccount() {
        forName = name?.text.toString()
        forRoll = roll?.text.toString()
        forPassword = pass?.text.toString()
        forEmail = email?.text.toString()

        //If fields are left empty
        if (!TextUtils.isEmpty(forName) && !TextUtils.isEmpty(forPassword) && !TextUtils.isEmpty(forEmail) && !TextUtils.isEmpty(forRoll)){
            ;
        }
        else {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(forEmail!!, forPassword!!)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    //If user registration successful, display success message
                    Log.d(forTAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(this, "User registered successfully.", Toast.LENGTH_SHORT).show()
                }
                else {

                    //If registration fails, display failure message.
                    Log.w(forTAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
