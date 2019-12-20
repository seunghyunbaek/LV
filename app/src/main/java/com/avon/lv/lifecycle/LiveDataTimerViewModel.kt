package com.avon.lv.lifecycle

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


class LiveDataTimerViewModel : ViewModel() {

    // 사용자 지정 크로노미터로 변경
    // 타이머를 사용하여 1초마다 UI변경
    // ViewModel에서 UI를 직접 수정하지 않고 데이터 원본을 관찰하고 있다가 데이터가 변경될 때 수신받음

    companion object {
        val ONE_SECOND: Long = 1000
    }

    private val mElapsedTime: MutableLiveData<Long> = MutableLiveData<Long>()

    private var mInitialTime: Long
    private val timer: Timer

    init {
        mInitialTime = SystemClock.elapsedRealtime()
        timer = Timer()

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000

                mElapsedTime.postValue(newValue)
            }
        }, ONE_SECOND, ONE_SECOND)
    }

    fun getElapsedTime(): LiveData<Long> {
        return mElapsedTime
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}