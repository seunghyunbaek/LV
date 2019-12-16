package com.avon.lv.TransactionFragment

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.avon.lv.R
import kotlinx.android.synthetic.main.activity_fragment_transaction.*

class FragmentTransactionActivity : AppCompatActivity(), View.OnClickListener {

    /*
        * 프래그먼트 추가와 삭제는 트랜잭션 단위로 한다
        * 프래그먼트 추가는 ViewGroup에 한다 ( 여기에서는 LinearLayout )
        * 액티비티와 마찬가지로 백스택이 있다

        아울러 이번에는 뒤로가기 키를 눌러 백스택에서 꺼내지 않아도 괜찮습니다
        삭제 버튼을 누르면 백스택으로부터 pop하고 한 단계 이전 상태로 돌아갑니다
        즉, 추가된 프래그먼트가 삭제됩니다
     */

    private val FRAGMENT_TAG = "FRAGMENT_TAG"
    private val FRAGMENT_TAG2 = "FRAGMENT_TAG2"
    private val KEY_NUMBER = "KEY_NUMBER"
    private var mNumber = 0
    private val mListener = FragmentManager.OnBackStackChangedListener {
        val fragmentManager = supportFragmentManager
        var count = 0
        fragmentManager.fragments.forEach {
            if (it.tag == FRAGMENT_TAG)
                count++
            else if (it.tag == FRAGMENT_TAG2)
                count++
        }
        mNumber = count
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_transaction)

        add_button.setOnClickListener {
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction() // 트랜잭션 생성
                .add(
                    R.id.fragment_container,
                    FragmentTransactionFragment.getInstance(mNumber),
                    FRAGMENT_TAG
                ) // 프래그먼트 생성
                .addToBackStack(null) // 백스택에 추가
                .commit()
        }

        remove_button.setOnClickListener {
            if (mNumber == 0) {
                return@setOnClickListener
            }
            val fragmentManager = supportFragmentManager
            fragmentManager.popBackStack()
        }

        val fragmentManager = supportFragmentManager
        fragmentManager.addOnBackStackChangedListener(mListener)

        val fragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG)

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                .add(
                    R.id.fragment_container,
                    FragmentTransactionFragment.getInstance(mNumber),
                    FRAGMENT_TAG
                )
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val fragmentManager = supportFragmentManager
        fragmentManager.removeOnBackStackChangedListener(mListener)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState!!.putInt(KEY_NUMBER, mNumber)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        mNumber = savedInstanceState!!.getInt(KEY_NUMBER)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount < 2) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.add_button2 -> {
                val fragmentManager = supportFragmentManager
                fragmentManager.beginTransaction() // 트랜잭션 생성
                    .add(
                        R.id.fragment_container,
                        FragmentTransactionFragment.getInstance(mNumber),
                        FRAGMENT_TAG2
                    ) // 프래그먼트 생성
                    .addToBackStack(null) // 백스택에 추가
                    .commit()
            }
            R.id.remove_button2 -> {
                if (mNumber == 0) {
                    return
                }
                val fragmentManager = supportFragmentManager
                fragmentManager.popBackStack()
            }
        }
    }
}
