package com.example.rockpaperscissorsapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val player1Spinner = findViewById<Spinner>(R.id.player1Spinner)
        val player2Spinner = findViewById<Spinner>(R.id.player2Spinner)

        val options = arrayOf("Rock", "Paper", "Scissors")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        player1Spinner.adapter = adapter
        player2Spinner.adapter = adapter

        val resultTextView = findViewById<TextView>(R.id.resultText)

        val checkWinnerButton = findViewById<Button>(R.id.checkWinnerButton)

        checkWinnerButton.setOnClickListener {
            val player1Choice = player1Spinner.selectedItem.toString()
            val player2Choice = player2Spinner.selectedItem.toString()

            val result = determineWinner(player1Choice, player2Choice)

            resultTextView.text = result
        }

        val infoButton = findViewById<ImageButton>(R.id.infoButton)
        infoButton.setOnClickListener {
            val intent = Intent(this, InstructionsActivity::class.java)
            startActivity(intent)
        }

    }

    private fun determineWinner(player1Choice: String, player2Choice: String): String {
        return when {
            player1Choice == player2Choice -> "It's a draw! \uD83D\uDE32"
            (player1Choice == "Rock" && player2Choice == "Scissors") ||
                    (player1Choice == "Paper" && player2Choice == "Rock") ||
                    (player1Choice == "Scissors" && player2Choice == "Paper") -> "$player1Choice beats $player2Choice. \uD83D\uDE32"
            else -> "$player2Choice beats $player1Choice. \uD83D\uDE32"
        }
    }
}
