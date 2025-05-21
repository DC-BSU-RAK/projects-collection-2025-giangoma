package com.example.lettersunsent

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadMainScreen()
    }

    private fun loadMainScreen() {
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        MediaPlayerManager.startMusic(this, sharedPreferences)

        val userName = sharedPreferences.getString("userName", "[Name]")
        val nameText = findViewById<TextView>(R.id.nameText)
        nameText.text = userName

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val infoButton = findViewById<ImageButton>(R.id.infoButton)
        val settingsButton = findViewById<ImageButton>(R.id.settingsButton)

        btn1.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.screen1, null)
            setContentView(view)

            ReadingVolumeManager.init(this)

            view.findViewById<ImageButton>(R.id.homeButton).setOnClickListener {
                ReadingAudioManager.stopAudio()
                loadMainScreen()
            }

            view.findViewById<ImageButton>(R.id.playAudioButton).setOnClickListener {
                ReadingAudioManager.playAudio(this, R.raw.ltustory1)
            }
        }

        btn2.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.screen2, null)
            setContentView(view)

            ReadingVolumeManager.init(this)

            view.findViewById<ImageButton>(R.id.homeButton).setOnClickListener {
                ReadingAudioManager.stopAudio()
                loadMainScreen()
            }

            view.findViewById<ImageButton>(R.id.playAudioButton).setOnClickListener {
                ReadingAudioManager.playAudio(this, R.raw.ltustory2)
            }
        }

        btn3.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.screen3, null)
            setContentView(view)

            ReadingVolumeManager.init(this)

            view.findViewById<ImageButton>(R.id.homeButton).setOnClickListener {
                ReadingAudioManager.stopAudio()
                loadMainScreen()
            }

            view.findViewById<ImageButton>(R.id.playAudioButton).setOnClickListener {
                ReadingAudioManager.playAudio(this, R.raw.ltustory3)
            }
        }

        btn4.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.screen4, null)
            setContentView(view)

            ReadingVolumeManager.init(this)

            view.findViewById<ImageButton>(R.id.homeButton).setOnClickListener {
                ReadingAudioManager.stopAudio()
                loadMainScreen()
            }

            view.findViewById<ImageButton>(R.id.playAudioButton).setOnClickListener {
                ReadingAudioManager.playAudio(this, R.raw.ltustory4)
            }
        }

        infoButton.setOnClickListener {
            val infoView = layoutInflater.inflate(R.layout.info_screen, null)
            setContentView(infoView)

            val infoSettingsBtn = infoView.findViewById<ImageButton>(R.id.settingsButton)
            val homeBtn = infoView.findViewById<ImageButton>(R.id.homeButton)

            infoSettingsBtn.setOnClickListener {
                loadSettingsScreen()
            }

            homeBtn.setOnClickListener {
                loadMainScreen()
            }
        }

        settingsButton.setOnClickListener {
            loadSettingsScreen()
        }
    }

    private fun loadSettingsScreen() {
        val settingsView = layoutInflater.inflate(R.layout.settings_screen, null)
        setContentView(settingsView)

        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val userName = sharedPreferences.getString("userName", "[Name]")
        val userEmail = sharedPreferences.getString("userEmail", "[Email]")

        val nameText = settingsView.findViewById<TextView>(R.id.settingsNameText)
        val emailText = settingsView.findViewById<TextView>(R.id.settingsEmailText)

        nameText.text = userName
        emailText.text = userEmail

        val homeBtn = settingsView.findViewById<ImageButton>(R.id.homeButton)
        val infoBtn = settingsView.findViewById<ImageButton>(R.id.infoButton)

        homeBtn.setOnClickListener {
            loadMainScreen()
        }

        infoBtn.setOnClickListener {
            val infoView = layoutInflater.inflate(R.layout.info_screen, null)
            setContentView(infoView)

            val infoSettingsBtn = infoView.findViewById<ImageButton>(R.id.settingsButton)
            val homeBtnInfo = infoView.findViewById<ImageButton>(R.id.homeButton)

            infoSettingsBtn.setOnClickListener {
                loadSettingsScreen()
            }

            homeBtnInfo.setOnClickListener {
                loadMainScreen()
            }
        }

        val volumeSeekBar = settingsView.findViewById<SeekBar>(R.id.volumeSeekBar)
        volumeSeekBar.progress = MediaPlayerManager.getVolume()

        volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                MediaPlayerManager.setVolume(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}
