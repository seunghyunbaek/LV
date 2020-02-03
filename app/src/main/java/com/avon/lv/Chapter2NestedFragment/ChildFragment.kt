package com.avon.lv.Chapter2NestedFragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.avon.lv.R
import kotlinx.android.synthetic.main.fragment_child.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ChildFragment : Fragment() {

    companion object {
        val ARG_NO: String = "ARG_NO"

        fun getInstance(no: Int): ChildFragment {
            val fragment: ChildFragment = ChildFragment()
            val args: Bundle = Bundle()
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
        return inflater.inflate(R.layout.fragment_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = view.findViewById(R.id.text_child)
        val no: Int = arguments!!.getInt(ARG_NO, 0)
        val text: String = "" + no + " 번째 자식 프래그먼트"
        textView.text = text
        textView.rotationX += 15

        if (no % 2 == 0) {
            btn_top_child.visibility = View.INVISIBLE
        } else {
            btn_bottom_child.visibility = View.GONE
        }

        btn_top_child.setOnClickListener {
            if (no % 2 == 0)
                Toast.makeText(view.context, "" + no + " 번째 자식", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(view.context, "" + no + " 번째 자식", Toast.LENGTH_LONG).show()
        }

        btn_bottom_child.setOnClickListener {
            if (no % 2 == 0)
                Toast.makeText(view.context, "" + no + " 번째 자식", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(view.context, "" + no + " 번째 자식", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ChildFragment", "onDestroy")
    }
}
