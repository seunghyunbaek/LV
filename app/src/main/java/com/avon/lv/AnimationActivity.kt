package com.avon.lv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class AnimationActivity : AppCompatActivity() {

    lateinit var translateLeftAnim: Animation
    lateinit var translateRightAnim: Animation
    lateinit var slidingPanel: LinearLayout
    lateinit var button: Button

    internal var isPageOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left)
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right)

        translateLeftAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                if (isPageOpen) {
                    slidingPanel.visibility = View.INVISIBLE
                    button.text = "열기"
                    isPageOpen = false
                } else {
                    button.text = "닫기"
                    isPageOpen = true
                }
            }

            override fun onAnimationStart(animation: Animation?) {}
        })

        translateRightAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                if (isPageOpen) {
                    slidingPanel.visibility = View.INVISIBLE
                    button.text = "열기"
                    isPageOpen = false
                } else {
                    button.text = "닫기"
                    isPageOpen = true
                }
            }

            override fun onAnimationStart(animation: Animation?) {}
        })

        slidingPanel = findViewById(R.id.slidingPanel) as LinearLayout
        button = findViewById(R.id.button) as Button

        button.setOnClickListener {
            if (isPageOpen) {
                slidingPanel.startAnimation(translateRightAnim)
                slidingPanel.visibility = View.GONE
            } else {
                slidingPanel.startAnimation(translateLeftAnim)
                slidingPanel.visibility = View.VISIBLE
            }
        }
//        rv.layoutManager = LinearLayoutManager(this)
//        rv.adapter = TAdapter()
    }

    inner class TAdapter : RecyclerView.Adapter<TAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TAdapter.ViewHolder {
            val view =
                LayoutInflater.from(applicationContext).inflate(R.layout.item_test, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return 30
        }

        override fun onBindViewHolder(holder: TAdapter.ViewHolder, position: Int) {
            holder?.text_item.text = "테스트용 아이템"
        }

        inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
            val text_item = itemView?.findViewById<TextView>(R.id.text_test_item) as TextView
        }
    }
}
