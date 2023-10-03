package com.simple.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FrameLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.framelayout_coordnate)
        setContentView(R.layout.frame_overlap_layout)
       // title = "framelayout_coordnate.xml"
        title = "frame_overlap_layout.xml"
    }
}
