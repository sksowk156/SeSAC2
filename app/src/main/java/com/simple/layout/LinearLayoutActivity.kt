package com.simple.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LinearLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.linear_layout_calculator)
        //title = "linear_layout_calculator.xml"
        setContentView(R.layout.linear_weight)
        title = "linear_weight"
    }
}
