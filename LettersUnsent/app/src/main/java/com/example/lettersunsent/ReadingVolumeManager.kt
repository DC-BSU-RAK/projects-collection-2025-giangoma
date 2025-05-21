package com.example.lettersunsent

import android.content.Context
import android.content.SharedPreferences

object ReadingVolumeManager {
    private const val PREF_NAME = "reading_audio_prefs"
    private const val KEY_READING_VOLUME = "reading_volume"
    private var sharedPreferences: SharedPreferences? = null

    fun init(context: Context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        }
    }

    fun getVolume(): Int {
        return sharedPreferences?.getInt(KEY_READING_VOLUME, 100) ?: 100
    }

    fun setVolume(volume: Int) {
        sharedPreferences?.edit()?.putInt(KEY_READING_VOLUME, volume)?.apply()
        ReadingAudioManager.setVolume(volume / 100f)
    }
}
