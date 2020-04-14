package com.avon.lv

import com.avon.lv.Chapter7BMICalculation.BmiCalculator
import com.avon.lv.Chapter7BMICalculation.BmiValue
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import org.mockito.Spy

class BmiCalculatorTest {

    @Spy
    private var calculator:BmiCalculator = BmiCalculator()

    @Before
    fun setup() { MockitoAnnotations.initMocks(this) }

    @Test(expected = RuntimeException::class)
    fun 신장에음수값을넘기면예외가발생한다() {calculator.calculate(-1f, 60.0f)}

    @Test(expected = RuntimeException::class)
    fun 체중에음수값을넘기면예외가발생한다() {calculator.calculate(170.0f, -1f)}

    @Test
    fun 단순한값을넘기면BMI가계산된다() {
        val result:BmiValue = calculator.calculate(1.70f, 60f)
        Assert.assertNotNull(result)
        Mockito.verify(calculator, times(1)).createValueObj(20.761246f)
    }
}