package com.avon.lv.lifecycle

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.avon.lv.R

class ChronoActivity : AppCompatActivity() {

    private val mLiveDataTimerViewModel: LiveDataTimerViewModel by lazy {
        ViewModelProvider(this).get(LiveDataTimerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chrono)

        // Chronometer 3
        subscribe()


        /* Chronometer 2
        val chronometerViewModel = ViewModelProvider(this).get(ChronometerViewModel::class.java)
        val chronometer = findViewById<Chronometer>(R.id.chronometer)

        if (chronometerViewModel.getStartTime() == null) {
            val startTime: Long = SystemClock.elapsedRealtime()
            chronometerViewModel.setStartTime(startTime)
            chronometer.base = startTime
        } else {
            chronometer.base = chronometerViewModel.getStartTime()!!
        }

        chronometer.start()
        */
    }

    fun subscribe() {
        val elapsedTimeObserver: Observer<Long> by lazy {
            object : Observer<Long> {
                override fun onChanged(aLong: Long?) {
                    var newText: String =
                        this@ChronoActivity.resources.getString(R.string.seconds, aLong)
                    findViewById<TextView>(R.id.timer_textview).setText(newText)
                    Log.d("ChronoActivity3", "UpdatingTimer")
                }
            }
        }

        //TODO: observe the ViewModel's elapsed time
        mLiveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
    }
}
