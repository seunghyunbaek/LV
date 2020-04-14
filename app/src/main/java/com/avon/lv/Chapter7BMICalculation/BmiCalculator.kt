package com.avon.lv.Chapter7BMICalculation

import androidx.annotation.VisibleForTesting
import java.lang.RuntimeException

class BmiCalculator {
    /*
    BMI 값을 계산해서 반환한다.
    @param heightInMeter BMI 계산에 사용할 신장
    @param weightInKg BMI 계산에 사용할 체중
    @return BMI 값
     */

    fun calculate(heightInMeter:Float, weightInKg:Float):BmiValue {
        if(heightInMeter < 0 || weightInKg < 0) {
            throw RuntimeException("키와 몸무게는 양수로 지정해주세요")
        }
        val bmiValue:Float = weightInKg / (heightInMeter * heightInMeter)
        return createValueObj(bmiValue)
    }

    @VisibleForTesting
    fun createValueObj(bmiValue:Float):BmiValue { return BmiValue(bmiValue)}
}