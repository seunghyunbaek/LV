package com.avon.lv

import com.avon.lv.Chapter7BMICalculation.BmiValue
import org.junit.Assert
import org.junit.Test

class BmiValueTest {

    @Test
    fun 생성시에전달한Float값을소수점2자리까지반올림해반환한다() {
        val bmiValue = BmiValue(20.00511f)
        Assert.assertEquals(20.01f, bmiValue.toFloat())
    }

    @Test
    fun 생성시에전달한Float값을소수점2자리까지버림해반환한다() {
        val bmiValue = BmiValue(20.00499f)
        Assert.assertEquals(20.00f, bmiValue.toFloat())
    }

    @Test
    fun 마른체형판정상한값() {
        val bmiValue = BmiValue(18.499f)
        Assert.assertEquals(BmiValue.THIN, bmiValue.getMessage())
    }

    @Test
    fun 보통체형판정하한값() {
        val bmiValue = BmiValue(18.500f)
        Assert.assertEquals(BmiValue.NORMAL, bmiValue.getMessage())
    }

    @Test
    fun 보통체형판정상한값() {
        val bmiValue = BmiValue(24.999f)
        Assert.assertEquals(BmiValue.NORMAL, bmiValue.getMessage())
    }

    @Test
    fun 경도비만판정하한값() {
        val bmiValue = BmiValue(25.000f)
        Assert.assertEquals(BmiValue.OBESITY, bmiValue.getMessage())
    }

    @Test
    fun 경도비만판정상한값() {
        val bmiValue = BmiValue(29.999f)
        Assert.assertEquals(BmiValue.OBESITY, bmiValue.getMessage())
    }

    @Test
    fun 중도비만판정상한값() {
        val bmiValue = BmiValue(30.000f)
        Assert.assertEquals(BmiValue.VERY_OBESITY, bmiValue.getMessage())
    }
}