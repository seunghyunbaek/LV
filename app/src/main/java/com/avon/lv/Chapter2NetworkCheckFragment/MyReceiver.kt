package com.avon.lv.Chapter2NetworkCheckFragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val i = Intent(NetworkCheckFragment.ACTION_CHECK_INTERNET)
        i.putExtra(
            NetworkCheckFragment.KEY_CHECK_INTERNET,
            NetworkCheckFragment.isInternetConnected(context)
        )

        // 연결 변경 알림
        // 네트워크 변경이 감지되면 MyReceiver에서 LocalBroadcastManager로 통지합니다.
        // 그 통지를 NetworkCheckFragment쪽에서 받아서 공통 처리를 구현할 수 있습니다.
        LocalBroadcastManager.getInstance(context).sendBroadcast(i)
    }
}