package com.example.lettersunsent

import android.content.Context
import android.media.MediaPlayer

object ReadingAudioManager {
    private var mediaPlayer: MediaPlayer? = null
    private var currentResId: Int? = null

    fun playAudio(context: Context, resId: Int) {
        if (mediaPlayer?.isPlaying == true && currentResId == resId) {
            stopAudio()
            return
        }

        stopAudio()

        mediaPlayer = MediaPlayer.create(context, resId)
        currentResId = resId

        val volume = ReadingVolumeManager.getVolume() / 100f
        mediaPlayer?.setVolume(volume, volume)
        mediaPlayer?.start()
    }

    fun stopAudio() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        currentResId = null
    }

    fun setVolume(volume: Float) {
        mediaPlayer?.setVolume(volume, volume)
    }
}
