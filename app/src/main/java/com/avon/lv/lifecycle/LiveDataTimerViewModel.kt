package com.avon.lv.lifecycle

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class LiveDataTimerViewModel : ViewModel() {
    companion object {
        val ONE_SECOND: Int = 1000
    }

    private val mElasedTime: MutableLiveData<Long> = MutableLiveData<Long>()

    private var mInitialTime:Long
    private val timer:Timer

    init {
        mInitialTime = SystemClock.elapsedRealtime()
        timer = Timer()
        
    }
}