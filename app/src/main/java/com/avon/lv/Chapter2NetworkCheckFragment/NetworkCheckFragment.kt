package com.avon.lv.Chapter2NetworkCheckFragment


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.avon.lv.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NetworkCheckFragment : Fragment() {

    companion object {
        val TAG: String = NetworkCheckFragment.javaClass.simpleName
        val ACTION_CHECK_INTERNET: String = "ACTION_CHECK_INTERNET"
        val KEY_CHECK_INTERNET: String = "KEY_CHECK_INTERNET"

        fun newInstance(): NetworkCheckFragment {
            return NetworkCheckFragment()
        }

        fun isInternetConnected(context: Context): Boolean {
            return isWifiConnected(context) || isMobileConnected(context)
        }

        fun isWifiConnected(context: Context): Boolean {
            val manager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            return if (info != null && info.isConnected) {
                true
            } else false
        }

        fun isMobileConnected(context: Context): Boolean {
            val manager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            return if (info != null && info.isConnected) {
                true
            } else false
        }
    }

    private var mIntentFilter: IntentFilter? = null

    private val mReceiver: BroadcastReceiver =
        object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                val action = intent.action
                if (ACTION_CHECK_INTERNET.equals(action)) { // 네트워크 연결 변경에 따른 공통 처리
                    val isConnected =
                        intent.getBooleanExtra(KEY_CHECK_INTERNET, false)
                    if (isConnected) { // 인터넷 연결이 있는 경우
                        Toast.makeText(context, "인터넷 연결 있음", Toast.LENGTH_SHORT).show()
                    } else { // 인터넷 연결이 없는 경우
                        Toast.makeText(context, "인터넷 연결 없음", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return TextView(activity).apply {
            setText(R.string.hello_blank_fragment)
        }
    }

    // 화면 회전 등 설저이 변경되더라도 프래그먼트를 재생성할 필요가 없습니다.
    // retainInstance = true를 설정해 재생성되지 않게 합니다
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onResume() {
        super.onResume()
        if (mIntentFilter == null) {
            mIntentFilter = IntentFilter(ACTION_CHECK_INTERNET)
        }

        context?.let {
            LocalBroadcastManager.getInstance(it).registerReceiver(mReceiver, mIntentFilter!!)
        }
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(context!!).unregisterReceiver(mReceiver);
    }


}
