package com.avon.lv.lifecycle

import androidx.lifecycle.ViewModel

class ChronometerViewModel : ViewModel() {

    private var startTime: Long? = null

    fun getStartTime(): Long? {
        return startTime
    }

    fun setStartTime(startTime: Long) {
        this.startTime = startTime
    }
}