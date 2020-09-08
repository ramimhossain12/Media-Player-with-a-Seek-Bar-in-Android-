package com.example.mediaplayerwithaseekbarinandroid

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.service.controls.actions.FloatAction
import android.util.Log
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {


    private  var mp: MediaPlayer? = null
   private  var currentSong = mutableListOf(R.raw.sunna)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        contolSound(currentSong[0])

    }

    private fun contolSound(i: Int) {
        fabplayID.setOnClickListener {
            if (mp == null){
                mp = MediaPlayer.create(this,i)
                Log.d("MainActivity","ID :${mp!!.audioSessionId}")

                initialseSeekBar()
            }

            mp?.start()
            Log.d("MainActivity","Duration :${mp!!.duration/1000} seconds")
        }

        fabpauseID.setOnClickListener {
            if (mp !=null) mp?.pause()

             Log.d("MainActivity","Paused at: ${mp!!.currentPosition/1000} seconds")

        }

        fabstopID.setOnClickListener {
            if (mp!=null){
                mp?.stop()
                mp?.reset()
                mp?.release()
                mp = null
            }
        }

        // Seek bar change listener
        seekbarID.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {

                if (b)mp?.seekTo(i)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
    }

    private fun initialseSeekBar() {


        seekbarID.max = mp!!.duration

        val handler = Handler()
        handler.postDelayed(object: Runnable {

            override fun run() {
                try {
                    seekbarID.progress = mp!!.currentPosition
                    handler.postDelayed(this,1000)
                } catch (e: Exception){
                    seekbarID.progress =0
                }

            }


        } ,0)
    }
}







