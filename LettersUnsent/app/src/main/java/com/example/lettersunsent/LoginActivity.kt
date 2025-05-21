package com.example.lettersunsent

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        // If already logged in, skip to MainActivity
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_login)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val emailInput = findViewById<EditText>(R.id.emailInput)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty()) {
                // Save login status and user info
                sharedPreferences.edit().apply {
                    putBoolean("isLoggedIn", true)
                    putString("userName", name)
                    putString("userEmail", email)
                    apply()
                }

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {

                nameInput.error = if (name.isEmpty()) "Enter name" else null
                emailInput.error = if (email.isEmpty()) "Enter email" else null
            }
        }
    }
}
