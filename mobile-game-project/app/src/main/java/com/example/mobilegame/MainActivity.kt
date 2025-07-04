package com.example.mobilegame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startGameButton = findViewById<Button>(R.id.startGameButton)
        val settingsButton = findViewById<Button>(R.id.settingsButton)

        startGameButton.setOnClickListener {
            // Oyuna başla ekranına geçiş yapılacak
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        settingsButton.setOnClickListener {
            // Ayarlar ekranına geçiş yapılacak
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}