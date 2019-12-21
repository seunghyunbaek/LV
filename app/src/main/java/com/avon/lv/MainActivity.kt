package com.avon.lv

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.avon.lv.NestedFragment.NestedFragmentActivity
import com.avon.lv.databinding.DatabindingActivity
import com.avon.lv.NetworkCheckFragment.NetworkCheckActivity
import com.avon.lv.SimpleFragment.MyFragmentActivity
import com.avon.lv.TransactionFragment.FragmentTransactionActivity
import com.avon.lv.lifecycle.ChronoActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

// https://github.com/wikibook/advanced-android-book
class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnclickListener()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        if(outState != null) {
            outState.putString("click", btn_click.text.toString())
        } else {
            println("blank")
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        btn_click.text = savedInstanceState?.getString("click")
    }

    private fun setOnclickListener() {
        btn_databinding.setOnClickListener(this) // 데이터 바인딩 액티비티 이동
        btn_click.setOnClickListener(this)
        btn_transactionfragment_fragment.setOnClickListener(this)
        btn_simple_fragment.setOnClickListener(this)
        btn_network_fragment.setOnClickListener(this)
        btn_lifecycle_activity.setOnClickListener(this)
        btn_nested_activity.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_databinding -> { startActivity<DatabindingActivity>() }
            R.id.btn_simple_fragment -> {startActivity<MyFragmentActivity>()}
            R.id.btn_transactionfragment_fragment -> {startActivity<FragmentTransactionActivity>()}
            R.id.btn_network_fragment -> {startActivity<NetworkCheckActivity>()}
            R.id.btn_lifecycle_activity-> {startActivity<ChronoActivity>()}
            R.id.btn_nested_activity -> {startActivity<NestedFragmentActivity>()}
            R.id.btn_click -> { btn_click.text = "Click!!" }
        }
    }
}
