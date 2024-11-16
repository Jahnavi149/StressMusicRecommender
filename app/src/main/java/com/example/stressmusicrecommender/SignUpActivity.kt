package com.example.stressmusicrecommender

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val usernameInput: EditText = findViewById(R.id.username)
        val passwordInput: EditText = findViewById(R.id.password)
        val ageInput: EditText = findViewById(R.id.age)
        val genderInput: EditText = findViewById(R.id.gender)
        val termsCheckBox: CheckBox = findViewById(R.id.terms)
        val submitButton: Button = findViewById(R.id.submit)
        val signInLink: TextView = findViewById(R.id.sign_in_link)

        submitButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()
            val age = ageInput.text.toString()
            val gender = genderInput.text.toString()

            if (username.isBlank() || password.isBlank() || age.isBlank() || gender.isBlank()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else if (!termsCheckBox.isChecked) {
                Toast.makeText(this, "Please agree to the terms and conditions", Toast.LENGTH_SHORT).show()
            } else {
                // Simulate successful sign-up (in a real app, save the user data and handle errors)
                Toast.makeText(this, "Sign-up successful!", Toast.LENGTH_SHORT).show()

                // Navigate to Sign-In Activity
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
                finish() // Close the sign-up activity so the user can't go back to it
            }
        }

        // Navigate to Sign-In page
        signInLink.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish() // Close the sign-up activity
        }
    }
}
