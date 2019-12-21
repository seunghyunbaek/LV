package com.avon.lv.NestedFragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.avon.lv.R

class NestedFragmentActivity : AppCompatActivity() {

    companion object {
        val TAG_PARENT:String = "TAG_PARENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_fragment)

        val fragmentManager:FragmentManager = supportFragmentManager
        val parentFragment: Fragment? = fragmentManager.findFragmentByTag(TAG_PARENT)
        Log.d("MainActivity", "onCreate parentFragment=" + parentFragment)
        if (parentFragment == null) {
            fragmentManager.beginTransaction()
                .add(R.id.container_nestedfragment, ParentFragment.getInstance(), TAG_PARENT).commit()
        }
    }

    override fun onBackPressed() {
        val fragmentManager:FragmentManager = supportFragmentManager
        val parentFragment: Fragment? = fragmentManager.findFragmentByTag(TAG_PARENT)
        if (parentFragment != null && parentFragment.childFragmentManager.backStackEntryCount > 0) {
            parentFragment.childFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("NestedFragmentActivity", "onDestroy")
    }
}
