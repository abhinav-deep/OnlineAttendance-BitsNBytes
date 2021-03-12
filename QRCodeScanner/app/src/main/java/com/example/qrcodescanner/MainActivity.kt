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
import io.realm.Realm

class MainActivity : AppCompatActivity() {


   private lateinit var username : EditText
   private lateinit var password : EditText
   private lateinit var loginButton : Button
    private lateinit var register : Button
   private val defaultUser = "Admin"
   private val defaultPassword = "1234"
   private var isValid = false

    private val tag  = "CreateAccountActivity"

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Realm.init(this)        //initialize the Realm library
        initializer()

        register.setOnClickListener(){
            val intent = Intent(this,RegisterActivity::class.java).apply{;}
            startActivity(intent)
        }


        loginButton.setOnClickListener(){
              val usrInput = username.text.toString()
              val pwdInput = password.text.toString()

              if(usrInput.isEmpty() || pwdInput.isEmpty())
              {
                  Toast.makeText(this,"Enter valid username or password!",Toast.LENGTH_SHORT).show()
              }
              else
              {
                  validate(usrInput,pwdInput)

              }
        }
    }

   private fun initializer(){
       username = findViewById(com.example.qrcodescanner.R.id.usernameInput_id)
       password = findViewById(com.example.qrcodescanner.R.id.passwordInput_id)
       loginButton = findViewById(R.id.loginButton_id)
       register = findViewById(R.id.register_id)

       auth = Firebase.auth
    }

   private fun validate(usr:String,pwd:String){
     //  if(usr == defaultUser && pwd == defaultPassword)
       //    return true
       //return false

       auth.signInWithEmailAndPassword(usr, pwd)
           .addOnCompleteListener(this) { task ->
               if (task.isSuccessful) {
                   // Sign in success, update UI with the signed-in user's information
                   Log.d(tag, "signInWithEmail:success")
                   val user = auth.currentUser
                   Toast.makeText(this,"Login successful!",Toast.LENGTH_SHORT).show()

                   //code to move to next activity (use intent)
                   val intent = Intent(this,ScannerActivity::class.java).apply { ; }

                   intent.putExtra("Username", usr)

                   startActivity(intent)

                   //updateUI(user)
               } else {
                   // If sign in fails, display a message to the user.
                   Log.w(tag, "signInWithEmail:failure", task.exception)
                   Toast.makeText(this,"Invalid login credentials!",Toast.LENGTH_SHORT).show()
                   // ...
               }

               // ...
           }

   }
}
