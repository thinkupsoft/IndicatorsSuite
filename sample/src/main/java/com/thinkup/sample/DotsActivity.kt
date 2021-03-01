package com.thinkup.sample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.thinkup.dotsindicator.DotsView
import kotlinx.android.synthetic.main.activity_dots.*
import kotlinx.android.synthetic.main.activity_dots.subView
import kotlinx.android.synthetic.main.activity_dots.testList

class DotsActivity : AppCompatActivity(), DotsView.Callback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dots)
        dots.loadItems(3, 0)

        testNextView.setOnClickListener { dots.next() }
        testPreviousView.setOnClickListener { dots.previous() }

        testList.adapter = TestAdapter()
        testList.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        PagerSnapHelper().attachToRecyclerView(testList)
        dotsRec.attach(testList)
        dotsRec.setCallback(this)

        dotsAdd.setOnClickListener {
            subView.addView(
                DotsView.Builder(this)
                    .setWidth(R.dimen.dot_width_select)
                    .setSelectedWidth(R.dimen.dot_width_select)
                    .setHeight(R.dimen.dot_width_select)
                    .setSelectedColor(R.color.colorAccent)
                    .setUnselectedColor(R.color.colorPrimaryDark)
                    .setStepsCount(3)
                    .setCurrentIndex(1)
                    .setCallback(this)
                    .build()
            )
        }
    }

    override fun onIndexChange(previous: Int, current: Int) {
        Toast.makeText(this, "Previous:$previous, Current:$current", Toast.LENGTH_SHORT).show()
    }
}
