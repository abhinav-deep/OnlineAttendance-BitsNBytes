package com.example.qrcodescanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {


   private lateinit var username : EditText
   private lateinit var password : EditText
   private lateinit var loginButton : Button
   private val defaultUser = "Admin"
   private val defaultPassword = "1234"
   private var isValid = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializer()

        loginButton.setOnClickListener(){
              val usrInput = username.text.toString()
              val pwdInput = password.text.toString()

              if(usrInput.isEmpty() || pwdInput.isEmpty())
              {
                  Toast.makeText(this,"Enter valid username or password!",Toast.LENGTH_SHORT).show()
              }
              else
              {
                  isValid = validate(usrInput,pwdInput)
                  if(isValid)
                  {
                      Toast.makeText(this,"Login successful!",Toast.LENGTH_SHORT).show()

                      //code to move to next activity (use intent)
                      val intent = Intent(this,ScannerActivity::class.java).apply { ; }
                      startActivity(intent)
                  }
                  else
                  {
                      Toast.makeText(this,"Invalid login credentials!",Toast.LENGTH_SHORT).show()
                  }
              }
        }
    }

   private fun initializer(){
       username = findViewById(com.example.qrcodescanner.R.id.usernameInput_id)
       password = findViewById(com.example.qrcodescanner.R.id.passwordInput_id)
       loginButton = findViewById(R.id.loginButton_id)
    }

   private fun validate(usr:String,pwd:String): Boolean{
       if(usr == defaultUser && pwd == defaultPassword)
           return true
       return false
   }
}
