package com.simple.layout

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.simple.layout.databinding.LinearLayoutCalculatorBinding
import java.util.LinkedList

class CalculationActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: LinearLayoutCalculatorBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LinearLayoutCalculatorBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        with(binding) {
            Btn00.setOnClickListener(this@CalculationActivity)
            Btn01.setOnClickListener(this@CalculationActivity)
            Btn02.setOnClickListener(this@CalculationActivity)
            Btn03.setOnClickListener(this@CalculationActivity)
            Btn04.setOnClickListener(this@CalculationActivity)
            Btn05.setOnClickListener(this@CalculationActivity)
            Btn06.setOnClickListener(this@CalculationActivity)
            Btn07.setOnClickListener(this@CalculationActivity)
            Btn08.setOnClickListener(this@CalculationActivity)
            Btn09.setOnClickListener(this@CalculationActivity)
            BtnPlus.setOnClickListener(this@CalculationActivity)
            BtnMinus.setOnClickListener(this@CalculationActivity)
            BtnMul.setOnClickListener(this@CalculationActivity)
            BtnDiv.setOnClickListener(this@CalculationActivity)
            BtnPoint.setOnClickListener(this@CalculationActivity)

            BtnResult.setOnClickListener { // =
                makeEquation()
                calEquation()
            }

            BtnDel.setOnClickListener {// 지우기
                deleteResult()
            }

            BtnDel.setOnLongClickListener {
                binding.resultED.setText("0")
                true
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun addResult(component: String) {
        binding.resultED.setText((binding.resultED.text.toString() + component).trimStart('0', '.'))
    }

    fun deleteResult() {
        val before = binding.resultED.text.toString()
        val after = before.substring(0, before.length - 1)

        if (before.length <= 1) {
            binding.resultED.setText("0")
        } else {
            binding.resultED.setText(after)
        }
    }

    private val charNumList = mutableListOf<Char>()
    private val equation = mutableListOf<String>()
    private var hasMulDivOperation = false

    private fun makeEquation() {
        for (i in binding.resultED.text) {
            if (i == '*' || i == '/' || i == '+' || i == '-') {
                calMulDivInEquation()
                if (i == '*' || i == '/') hasMulDivOperation = true
                equation.add(i.toString())
            } else {
                charNumList.add(i)
            }
        }
        calMulDivInEquation()
    }

    private fun makeNum() = if (charNumList.size > 0) {
        val num = charNumList.joinToString("")
        charNumList.clear()
        num
    } else {
        "0.0"
    }

    private fun calMulDivInEquation() {
        equation.add(makeNum())
        if (hasMulDivOperation) {
            val right = equation.removeLast().toDouble()
            val operation = equation.removeLast()
            val left = equation.removeLast().toDouble()
            if (operation == "*") equation.add((left * right).toString())
            else equation.add((left / right).toString())
            hasMulDivOperation = false
        }
    }

    private fun calEquation() {
        var result = 0.0
        for (i in equation.indices) {
            if (equation[i] != "+" && equation[i] != "-") {
                if (i > 0 && equation[i - 1] == "-") {
                    result -= equation[i].toDouble()
                } else {
                    result += equation[i].toDouble()
                }
            }
        }
        equation.clear()
        binding.resultED.setText(result.toString())
    }

    override fun onClick(p0: View?) {
        with(binding) {
            when (p0) {
                Btn00 -> {
                    addResult("0")
                    if (binding.resultED.text.isEmpty()) binding.resultED.setText("0")
                }

                Btn01 -> {
                    addResult("1")
                }

                Btn02 -> {
                    addResult("2")
                }

                Btn03 -> {
                    addResult("3")
                }

                Btn04 -> {
                    addResult("4")
                }

                Btn05 -> {
                    addResult("5")
                }

                Btn06 -> {
                    addResult("6")
                }

                Btn07 -> {
                    addResult("7")
                }

                Btn08 -> {
                    addResult("8")
                }

                Btn09 -> {
                    addResult("9")
                }

                BtnPlus -> {
                    addResult("+")
                }

                BtnMinus -> {
                    addResult("-")
                }

                BtnMul -> {
                    addResult("*")
                }

                BtnDiv -> {
                    addResult("/")
                }

                BtnPoint -> {
                    addResult(".")
                }

            }
        }
    }

}