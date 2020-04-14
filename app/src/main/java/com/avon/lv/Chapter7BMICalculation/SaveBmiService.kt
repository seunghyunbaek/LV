package com.avon.lv.Chapter7BMICalculation

import android.app.IntentService
import android.content.Context
import android.content.Intent
import androidx.annotation.VisibleForTesting
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.io.Serializable
import java.util.*

class SaveBmiService: IntentService(SaveBmiService.javaClass.name) {
    companion object {
        val ACTION_RESULT = SaveBmiService::class.java.name + ".ACTION_RESULT"
        const val PARAM_RESULT = "param_result"
        const val PARAM_KEY_BMI_VALUE = "bmi_value"

        fun start(context: Context, bmiValue: BmiValue) {
            val intent:Intent = Intent(context, SaveBmiService::class.java)
            intent.putExtra(PARAM_KEY_BMI_VALUE, bmiValue)
            context.startService(intent)
        }
    }

    private lateinit var mLocalBroadcast:LocalBroadcastManager

    override fun onCreate() {
        super.onCreate()
        mLocalBroadcast = LocalBroadcastManager.getInstance(applicationContext)
    }

    override fun onHandleIntent(intent: Intent?) {
        if(intent == null) {
            return
        }

        val extra:Serializable = intent.getSerializableExtra(PARAM_KEY_BMI_VALUE)
        if(extra == null || extra !is BmiValue) {
            return
        }

        val bmiValue:BmiValue = extra
        val result:Boolean = saveToRemoteServer(bmiValue)
        sendLocalBroadcast(result)
    }

    @VisibleForTesting
    fun saveToRemoteServer(bmiValue: BmiValue):Boolean {
        try{
            Thread.sleep((3000 + Random().nextInt(2000)).toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        /*
        * 원래는 여기에 서버에 저장하는 코드를 작성한다.
        */

        return Random().nextBoolean()
    }

    @VisibleForTesting
    fun sendLocalBroadcast(result:Boolean) {
        val resultIntent:Intent = Intent(ACTION_RESULT)
        resultIntent.putExtra(PARAM_RESULT, result)
        mLocalBroadcast.sendBroadcast(resultIntent)
    }

    @VisibleForTesting
    fun setLocalBroadcastManager(manager:LocalBroadcastManager) {
        mLocalBroadcast = manager
    }
}