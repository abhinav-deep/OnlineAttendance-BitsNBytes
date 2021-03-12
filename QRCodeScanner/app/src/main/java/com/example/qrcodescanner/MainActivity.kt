package com.example.qrcodescanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    //declaring variables
    private lateinit var username : EditText
    private lateinit var password : EditText
    private lateinit var loginButton : Button
    private lateinit var register : Button
    private val tag  = "CreateAccountActivity"
    private lateinit var auth: FirebaseAuth

    //Default user credentials for testing purpose
    /*
    private val defaultUser = "Admin"
    private val defaultPassword = "1234"
    private var isValid = false
    */

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize variables
        initializer()

        //Moving to register activity for new users
        register.setOnClickListener(){
            val intent = Intent(this,RegisterActivity::class.java).apply{;}
            startActivity(intent)
        }

        //Moving to scanner activity for existing users
        loginButton.setOnClickListener(){
            val usrInput = username.text.toString()
            val pwdInput = password.text.toString()

            //If input fields left empty
            if(usrInput.isEmpty() || pwdInput.isEmpty()) {
                  Toast.makeText(this,"Enter valid username or password!",Toast.LENGTH_SHORT).show()
            }
            else {
                //Validate username and password
                validate(usrInput,pwdInput)
            }
        }
    }

    //initializing variables
    private fun initializer(){
       username = findViewById(com.example.qrcodescanner.R.id.usernameInput_id)
       password = findViewById(com.example.qrcodescanner.R.id.passwordInput_id)
       loginButton = findViewById(R.id.loginButton_id)
       register = findViewById(R.id.register_id)
       auth = Firebase.auth
    }

    //validating input username and password combination
    private fun validate(usr:String,pwd:String){

        //To access via default user (only for testing purpose)
        /*
        if(usr == defaultUser && pwd == defaultPassword){

            Toast.makeText(this,"Login successful!",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,ScannerActivity::class.java).apply { ; }
            intent.putExtra("Username", usr)
            startActivity(intent)
       }
       */
       auth.signInWithEmailAndPassword(usr, pwd)
           .addOnCompleteListener(this) { task ->
               if (task.isSuccessful) {

                   Log.d(tag, "signInWithEmail:success")
                   val user = auth.currentUser
                   Toast.makeText(this,"Login successful!",Toast.LENGTH_SHORT).show()

                   //Move to scanner activity with username if user validation is successful.
                   val intent = Intent(this,ScannerActivity::class.java).apply { ; }
                   intent.putExtra("Username", usr)
                   startActivity(intent)

               } else {
                   //If user validation fails, display failure message to the user.
                   Log.w(tag, "signInWithEmail:failure", task.exception)
                   Toast.makeText(this,"Invalid login credentials!",Toast.LENGTH_SHORT).show()

               }
           }
   }
}
