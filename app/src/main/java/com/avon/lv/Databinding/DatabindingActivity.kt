package com.avon.lv.Databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.avon.lv.R
import com.avon.lv.databinding.ActivityDatabindingBinding

/*
    안드로이드 데이터 바인딩 (바인딩 어댑터 진행중)
    site
    https://codelabs.developers.google.com/codelabs/android-databinding/#7
    github
    https://github.com/googlecodelabs/android-databinding/blob/master/app/src/main/res/layout/plain_activity_solution_4.xml
*/
class DatabindingActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(SimpleViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_databinding)

        // 데이터 바인딩 레이아웃을 사용하고 있어 인플레이션을 다른 방식으로 수행한다.
        val binding: ActivityDatabindingBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_databinding
        )
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
//        binding.name = "Your name"
//        binding.lastName = "Your last name"
    }

}