package com.avon.lv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.jetbrains.anko.toast

class MyFragmentActivity : AppCompatActivity(), MyFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_fragment)
    }

    // 리스너와 프래그먼트의 연결은 액티비티와 프래그먼트가 연결될 때 onAttach()에서 합니다
    // 또한 연결을 끊기 전에 onDetach()에서 리스너에 대한 참조를 해제합니다
    // onAttach()에서는 OnFragmentInteractionListener가 구현되지 않았으면 RuntimeException을 던져 처리를 계속할 수 없게 했습니다
    override fun onFragmentInteraction() {
        toast("버튼이 눌렸습니다")
    }
}
