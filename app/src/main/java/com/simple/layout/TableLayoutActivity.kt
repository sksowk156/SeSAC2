package com.simple.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TableLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table_layout)
        title = "table_layout.xml"
    }
}
