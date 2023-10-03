package com.simple.layout

import android.R
import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

@Suppress("DEPRECATION")
class MainActivity : ListActivity() {

    private val treeMaps = TreeMap<String, Intent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addActionMap("1.리니어레이아웃", CalculationActivity::class.java)
        addActionMap("2.프레임레이아웃", FrameLayoutActivity::class.java)
        addActionMap("3.상대레이아웃", RelativeLayoutActivity::class.java)
        addActionMap("4.테이블레이아웃", TableLayoutActivity::class.java)

        val keys = treeMaps.keys
        val keyNames: Array<String> = keys.toTypedArray()
        listAdapter = ArrayAdapter(this, R.layout.simple_list_item_1, keyNames)
    }
    private fun addActionMap(keyName: String, className: Class<*>) {
        treeMaps[keyName] = Intent(this, className)
    }
    @Deprecated("Deprecated in Java")
    override fun onListItemClick(listView: ListView, item: View?, position: Int, id: Long) {
        val keyName = listView.getItemAtPosition(position) as String
        startActivity(treeMaps[keyName])
    }
}
