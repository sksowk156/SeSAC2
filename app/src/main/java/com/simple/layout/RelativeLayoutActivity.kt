package com.simple.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class RelativeLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.relative_layout)
        title = "relative_layout.xml"
        //setContentView(R.layout.relativelayout_button)
        //title = "relativelayout_button.xml"

    }
}
