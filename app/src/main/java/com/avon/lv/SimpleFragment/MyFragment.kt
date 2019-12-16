package com.avon.lv.SimpleFragment


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avon.lv.R
import kotlinx.android.synthetic.main.fragment_my.*
import java.lang.RuntimeException


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MyFragment : Fragment() {

    private var mListener: OnFragmentInteractionListener? = null

    // 뷰를 생성
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false)
    }

    // 뷰 생성후 불리는 콜백 (onCreateView 다음
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            mListener?.onFragmentInteraction()
        }
    }

    // 이제 액티비티에서 구현한 리스너를 프래그먼트에서 가질 수 있고
    // 버튼이 눌린 시점을 액티비티에 알려줄 수 있게 됐습니다
    // MainActivity에 대한 참조를 직접 가지지 않고 인터페이스로서 가지는 것은 특정 액티비티에 의존하지 않도록 결합을 느슨하게 만들기 위해서입니다
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Activity 쪽에 필요한 인터페이스가 구현됐는지 확인
        if(context is OnFragmentInteractionListener) {
            mListener = context as OnFragmentInteractionListener
        } else {
            throw RuntimeException(context.toString() + "OnFragmentInteractionListener를 구현해주세요")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction()
    }
}
