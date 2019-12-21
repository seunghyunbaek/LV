package com.avon.lv.NestedFragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.avon.lv.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * 중첩 프래그먼트를 다룰 때는 childFragmentManager를 사용합니다.
 * 중첩 프래그먼트는 레이아웃 XML로 추가할 수 없고, 항상 동적으로 추가해야 합니다.
 * 부모 프래그먼트 쪽에서는 기본적으로 UI를 가지지 않고, 자식 프래그먼트 관리를 중심으로 하는 편이 역할을 명확히 나눔으로 복잡성이 줄고 동작의 예측이 가능해집니다.
 */
class ParentFragment : Fragment() {

    companion object {
        val TAG_CHILD: String = "TAG_CHILD"
        val KEY_NUMBER: String = "KEY_NUMBER"

        fun getInstance(): ParentFragment {
            return ParentFragment()
        }
    }

    private var mNumber: Int = 0


    private val mListener: FragmentManager.OnBackStackChangedListener =
        FragmentManager.OnBackStackChangedListener {
            val fragmentManager: FragmentManager = childFragmentManager
            var count: Int = 0
            fragmentManager.fragments.forEach {
                if (it != null) {
                    count++
                }
            }
            mNumber = count
            Log.d("ParentFragment", "onBackStackChanged mNumber=" + mNumber)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_add_parent).setOnClickListener {
            val childFragmentManager: FragmentManager = childFragmentManager
            mNumber = childFragmentManager.backStackEntryCount
            val key = TAG_CHILD + mNumber.toString()
            childFragmentManager.beginTransaction()
                .replace(R.id.container_parentfragment, ChildFragment.getInstance(mNumber), key)
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.btn_remove_parent).setOnClickListener {
            if (mNumber == 0) {
                return@setOnClickListener
            }

            val childFragmentManager: FragmentManager = childFragmentManager
            childFragmentManager.popBackStack()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("ParentFragment", "onActivityCreated")
        if (savedInstanceState != null) {
            mNumber = savedInstanceState.getInt(KEY_NUMBER, 0)
        }
        val childFragmentManager: FragmentManager = childFragmentManager
        val fragment: Fragment? = childFragmentManager.findFragmentByTag(TAG_CHILD)
        Log.d(
            "ParentFragment",
            "onActivityCreated childFragment=" + fragment + ", mNumber=" + mNumber
        )
        if (savedInstanceState == null) {
            val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
            transaction.add(
                R.id.container_parentfragment,
                ChildFragment.getInstance(mNumber),
                TAG_CHILD
            )
            transaction.addToBackStack(null)
            transaction.commit()
        }
        childFragmentManager.addOnBackStackChangedListener(mListener)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("ParentFragment", "onSaveInstanceState mNumber=" + mNumber)
        outState.putInt(KEY_NUMBER, mNumber)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ParentFragment", "onDestroy")
    }
}
