package com.example.qrcodescanner

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase



class RegisterActivity : AppCompatActivity() {


    private val forTAG  = "CreateAccountActivity"

    //global variables
    private var forName: String? = null
    private var forRoll: String? = null
    private var forPassword: String? = null
    private var forEmail: String? = null

    private lateinit var name: EditText
    private lateinit var roll: EditText
    private lateinit var pass: EditText
    private lateinit var email: EditText
    private lateinit var register: Button
    private var mProgressBar: ProgressDialog? = null

    /*Firebase references
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null


     */

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initialise()


    }


    private fun initialise() {
        name = findViewById(R.id.rgName_id)
        roll = findViewById(R.id.rgRoll_id)
        pass = findViewById(R.id.rgPassword_id)
        email = findViewById(R.id.rgEmail_id)
        register = findViewById(R.id.rgButton_id)

        mProgressBar = ProgressDialog(this)


        /*
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()
         */

        auth = Firebase.auth


        register.setOnClickListener {


            val currentUser = auth.currentUser
           // if (currentUser != null) {
             //   Toast.makeText(this, "User already exists!", Toast.LENGTH_SHORT).show();
            //}
           // else
                createNewAccount()
        }
    }


    private fun createNewAccount() {
        forName = name?.text.toString()
        forRoll = roll?.text.toString()
        forPassword = pass?.text.toString()
        forEmail = email?.text.toString()


        if (!TextUtils.isEmpty(forName) && !TextUtils.isEmpty(forPassword) && !TextUtils.isEmpty(forEmail) && !TextUtils.isEmpty(forRoll)){
            ;
        }
        else {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
            return
        }

       // mProgressBar!!.setMessage("Registering User...")
       //mProgressBar!!.show()

        /*
        mAuth!!
            .createUserWithEmailAndPassword(forEmail!!, forPassword!!)
            .addOnCompleteListener(this) { task ->
               // mProgressBar!!.hide()
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(forTAG, "createUserWithEmail:success")
                    val userId = mAuth!!.currentUser!!.uid
                    //Verify Email
                    //verifyEmail();
                    //update user profile information
                    val currentUserDb = mDatabaseReference!!.child(userId)
                    currentUserDb.child("Name").setValue(forName)
                    currentUserDb.child("Roll No.").setValue(forRoll)
                    updateUserInfoAndUI()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(forTAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


         */

        auth.createUserWithEmailAndPassword(forEmail!!, forPassword!!)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(forTAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(this, "User registered successfully.", Toast.LENGTH_SHORT).show()
                    //updateUI(user)
                }
                else {
                    // If sign in fails, display a message to the user.
                    Log.w(forTAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }

            }





    }
}
/*
    private fun verifyEmail() {
        val mUser = mAuth!!.currentUser;
        mUser!!.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,
                        "Verification email sent to " + mUser.email,
                        Toast.LENGTH_SHORT).show()
                } else {
                    Log.e(forTAG, "sendEmailVerification", task.exception)
                    Toast.makeText(this,
                        "Failed to send verification email.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun updateUserInfoAndUI() {
        //start next activity
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}
*/
