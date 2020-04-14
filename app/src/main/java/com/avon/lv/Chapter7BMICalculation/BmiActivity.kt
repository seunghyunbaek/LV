package com.avon.lv.Chapter7BMICalculation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.avon.lv.R

class BmiActivity : AppCompatActivity() {

    private lateinit var mLocalBroadcastReceiver:LocalBroadcastManager
    private lateinit var mCalculatorButton: Button
    private lateinit var mReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        mLocalBroadcastReceiver = LocalBroadcastManager.getInstance(applicationContext)

        initViews()
    }

    fun initViews() {
        val weightText = findViewById<EditText>(R.id.weight_bmi)
        val heightText = findViewById<EditText>(R.id.height_bmi)
        val resultText = findViewById<TextView>(R.id.result_calculation)

        mCalculatorButton = findViewById(R.id.btn_calculation)

        val buttonListener: View.OnClickListener= createButtonListener(weightText, heightText, resultText)
        mCalculatorButton.setOnClickListener(buttonListener)
    }

    @VisibleForTesting
    fun createButtonListener(weightText:TextView, heightText:TextView, resultText:TextView): View.OnClickListener {
        return View.OnClickListener {
            // 결과 취득 표시
            val result:BmiValue = calculateBmiValue(weightText, heightText)
            showCalcResult(resultText, result)

            // Service를 사용해 보존 처리
            startResultSaveService(result)
            prepareReceiveResultSaveServiceAction()
        }
    }

    @VisibleForTesting
    fun calculateBmiValue(weightText:TextView, heightText: TextView): BmiValue {
        val weight:Float = weightText.text.toString().toFloat()
        val height:Float = heightText.text.toString().toFloat()
        val calculator:BmiCalculator = BmiCalculator()

        return calculator.calculate(height, weight)
    }

    @VisibleForTesting
    fun showCalcResult(resultText: TextView, result:BmiValue) {
        val message:String = "${result.toFloat()} : ${result.getMessage()} 체형입니다"
        resultText.text = message
    }

    @VisibleForTesting
    fun startResultSaveService(result:BmiValue) {
        mCalculatorButton.text = "저장 중입니다..."
        mCalculatorButton.isEnabled = false
        SaveBmiService.start(this@BmiActivity, result)
    }

    @VisibleForTesting
    fun prepareReceiveResultSaveServiceAction() {
        val filter:IntentFilter = IntentFilter(SaveBmiService.ACTION_RESULT)
        mReceiver = BmiSaveResultReceiver(mCalculatorButton)
        mLocalBroadcastReceiver.registerReceiver(mReceiver, filter)
    }

    @VisibleForTesting
    override fun onDestroy() {
        super.onDestroy()
        if(mReceiver != null) {
            mLocalBroadcastReceiver.unregisterReceiver(mReceiver)
        }
    }

    internal class BmiSaveResultReceiver(private val mCalcButton: Button) : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            if (intent == null) {
                return
            }
            if (!intent.hasExtra(SaveBmiService.PARAM_RESULT)) {
                return
            }
            val result = intent.getBooleanExtra(SaveBmiService.PARAM_RESULT, false)
            if (!result) {
                Toast.makeText(context, "BMI 저장에 실패했습니다", Toast.LENGTH_SHORT).show()
            }

            mCalcButton.text = "계산한다"
            mCalcButton.isEnabled = true
        }
    }

}
