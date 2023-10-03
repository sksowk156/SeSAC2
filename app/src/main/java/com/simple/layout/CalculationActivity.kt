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

    /**
     * EditText에 누른 숫자를 입력해주는 메소드
     * trimStart('0','.') 으로 EditText의 첫글자가 '0'이거나 '.'일 경우 제거해 준다.
     *
     * @param component 는 누른 숫자를 의미한다.
     */
    @SuppressLint("SetTextI18n")
    fun addResult(component: String) {
        binding.resultED.setText((binding.resultED.text.toString() + component).trimStart('0', '.'))
    }

    /**
     * EditText에 글자 지우기 메소드
     * 지우기 버튼을 누르면 EditText의 글자가 뒤에서부터 하나씩 지워진다.
     *
     */
    fun deleteResult() {
        val before = binding.resultED.text.toString()
        val after = before.substring(0, before.length - 1)

        if (before.length <= 1) { // 현재 EditText에 있는 글자가 1개일 경우 지우면 0으로 초기화
            binding.resultED.setText("0")
        } else {
            binding.resultED.setText(after)
        }
    }

    private val charNumList = mutableListOf<Char>()
    private val equation = mutableListOf<String>()
    private var hasMulDivOperation = false

    /**
     * 식을 만들어주는 메소드
     * '='를 누르면 EditText에 적힌 문자열을 수학 식으로 계산할 수 있게끔 만들어준다.
     *
     */
    private fun makeEquation() {
        for (i in binding.resultED.text) {
            if (i == '*' || i == '/' || i == '+' || i == '-') { // 연산자가 나오면 그 앞의 char들을 하나의 String으로 바꿔준다.
                calMulDivInEquation() // '*'와 '/'는 연산자 우선순위가 높기 때문에 식에서 먼저 연산해서 지워준다. -> 식에는 '+'와 '-' 연산자만 남음
                if (i == '*' || i == '/') hasMulDivOperation = true
                equation.add(i.toString())
            } else {
                charNumList.add(i)
            }
        }
        calMulDivInEquation() // 마지막엔 숫자로 끝나기 때문에 한번 더
    }

    /**
     * char로 된 문자들을 하나의 String으로 변경해주는 메소드
     *
     */
    private fun makeNum() = if (charNumList.size > 0) {
        val num = charNumList.joinToString("")
        charNumList.clear()
        num
    } else {
        "0.0"
    }

    /**
     * 연산자 '*'와 '/'의 주변 숫자들 연산해주는 메소드
     * 문자열을 수학식으로 바꿀 때 우선순위가 높은 '*'와 '/'를 먼저 연산해준다.
     *
     */
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

    /**
     * 위에서 만들어진 수학식을 계산하는 메소드
     * 연산자 '*'와 '/'가 없기 때문에 '+'와 '-'만 연산하면 된다.
     * 우선순위가 없으므로 앞에서부터 순서대로 연산
     *
     */
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

    /**
     * 숫자 클릭 메소드
     *
     * @param p0 는 숫자 버튼이다.
     */
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