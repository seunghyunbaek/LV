package com.avon.lv.Chapter2NetworkCheckFragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.avon.lv.R

class NetworkCheckActivity : AppCompatActivity() {

    private var mFragment: NetworkCheckFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_check)

        // NetworkCheckFragment에서 정의한 TAG(문자열)로 프래그먼트가 추가됐는지 체크
        if ((supportFragmentManager.findFragmentByTag(NetworkCheckFragment.TAG) is NetworkCheckFragment))
            mFragment =
                supportFragmentManager.findFragmentByTag(NetworkCheckFragment.TAG) as NetworkCheckFragment

        if (mFragment == null) {
            mFragment = NetworkCheckFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .add(mFragment!!, NetworkCheckFragment.TAG)
                // 프래그먼트가 UI를 갖지않는 경우 ViewGroup의 레이아웃 ID는 지정할 필요가 없습니다.
                .commit()
        }
    }
}
