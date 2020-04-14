package com.avon.lv.Chapter7BMICalculation

import androidx.annotation.VisibleForTesting
import java.io.Serializable

class BmiValue :Serializable {
    companion object {
        private val serialVersionUID:Long = -4325336659053219895L

        @VisibleForTesting
        const val THIN:String = "마른"
        @VisibleForTesting
        const val NORMAL:String = "보통"
        @VisibleForTesting
        const val OBESITY = "비만（１도）"
        @VisibleForTesting
        const val VERY_OBESITY = "비만（２도）"
    }

    private var mValue:Float = 0f

    constructor(value:Float) { mValue = value}

    fun toFloat():Float { // 소수점 아래 2자리까지
        val rounded = Math.round(mValue * 100)
        return rounded / 100f
    }

    fun getMessage():String {
        return if (mValue < 18.5f) {
            THIN
        } else if (18.5 <= mValue && mValue < 25) {
            NORMAL
        } else if (25 <= mValue && mValue < 30) {
            OBESITY
        } else {
            VERY_OBESITY
        }
    }


}