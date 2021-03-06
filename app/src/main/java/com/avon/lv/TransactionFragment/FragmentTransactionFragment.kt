package com.avon.lv.TransactionFragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.avon.lv.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentTransactionFragment : Fragment() {


    companion object {
        val ARG_NO: String = "ARG_NO"

        fun getInstance(no: Int): FragmentTransactionFragment {
            val fragment = FragmentTransactionFragment()
            val args = Bundle()
            args.putInt(ARG_NO, no)
            fragment.arguments = args

            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view?.findViewById<TextView>(R.id.text_transaction_fragment)
        val no: Int? = arguments?.getInt(ARG_NO, 0)
        val text: String = "" + no + "번째 프래그먼트"
        textView?.text = text
    }
}