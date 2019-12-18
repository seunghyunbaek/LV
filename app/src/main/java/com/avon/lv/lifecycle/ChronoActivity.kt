package com.avon.lv.lifecycle

import android.os.Bundle
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import com.avon.lv.R

class ChronoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chrono)

//        val chronometerViewModel = ViewModelProvider(this).get(ChronometerViewModel::class.java)

        val chronometer = findViewById<Chronometer>(R.id.chronometer)

//        if (chronometerViewModel.getStartTime() == null) {
//            val startTime: Long = SystemClock.elapsedRealtime()
//            chronometerViewModel.setStartTime(startTime)
//            chronometer.base = startTime
//        } else {
//            chronometer.base = chronometerViewModel.getStartTime()!!
//        }

        chronometer.start()
    }
}
