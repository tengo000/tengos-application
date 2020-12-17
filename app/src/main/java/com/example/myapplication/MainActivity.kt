package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var signInButton: Button
    private lateinit var registrationButton: Button
    private lateinit var resetPasswordButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null){
            startActivity(Intent(this, PersonActivity::class.java))
            finish()
        }

        setContentView(R.layout.activity_main)



        inputEmail = findViewById(R.id.signInEmailEditText)
        inputPassword = findViewById(R.id.signInPasswordEditText)
        signInButton = findViewById(R.id.signInButton)
        registrationButton = findViewById(R.id.gotoRegisterButton)
        resetPasswordButton =findViewById(R.id.gotoPasswordResetButton)

        signInButton.setOnClickListener {

            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(this, "File is empty", Toast.LENGTH_LONG).show()
            } else{
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this, PersonActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Error!!!", Toast.LENGTH_SHORT).show()
                        }
                    }

            }

        }

        registrationButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        resetPasswordButton.setOnClickListener {
            startActivity(Intent(this, ResetActivity::class.java))
        }



    }

}