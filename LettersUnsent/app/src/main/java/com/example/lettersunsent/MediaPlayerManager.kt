package com.example.lettersunsent

import android.content.Context
import android.media.MediaPlayer

object MediaPlayerManager {
    private var mediaPlayer: MediaPlayer? = null
    private var hasStarted = false
    private var sharedPreferences: android.content.SharedPreferences? = null


    fun startMusic(context: Context, prefs: android.content.SharedPreferences) {
        if (!hasStarted) {
            sharedPreferences = prefs
            mediaPlayer = MediaPlayer.create(context.applicationContext, R.raw.ltubgm)
            mediaPlayer?.isLooping = true

            val volume = getVolume() / 100f
            mediaPlayer?.setVolume(volume, volume)

            mediaPlayer?.start()
            hasStarted = true
        }
    }


    fun setVolume(progress: Int) {
        val volume = progress / 100f
        mediaPlayer?.setVolume(volume, volume)
        sharedPreferences?.edit()?.putInt("audioVolume", progress)?.apply()
    }

    fun getVolume(): Int {
        return sharedPreferences?.getInt("audioVolume", 100) ?: 100
    }



    fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        hasStarted = false
    }
}
