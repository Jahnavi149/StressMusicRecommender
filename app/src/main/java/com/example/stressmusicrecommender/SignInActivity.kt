package com.example.stressmusicrecommender

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val usernameInput: EditText = findViewById(R.id.username)
        val passwordInput: EditText = findViewById(R.id.password)
        val signInButton: Button = findViewById(R.id.sign_in)
        val signUpTextView: TextView = findViewById(R.id.sign_up)

        signInButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
            } else {
                // Simulate successful sign-in (in a real app, verify the credentials with backend)
                Toast.makeText(this, "Sign-in successful!", Toast.LENGTH_SHORT).show()

                // Navigate to Home Page Activity
                val intent = Intent(this, HomePageActivity::class.java)
                startActivity(intent)
                finish() // Close the sign-in activity
            }
        }

        // Navigate to Sign-Up page
        signUpTextView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
