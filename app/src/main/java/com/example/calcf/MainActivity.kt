package com.example.calculator

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.calcf.R

class MainActivity : AppCompatActivity() {

    private lateinit var txtCalc: EditText
    private lateinit var btn0: Button
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button
    private lateinit var btnAdd: Button
    private lateinit var btnSubtract: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivide: Button
    private lateinit var btnClear: Button
    private lateinit var btnEqual: Button

    private var firstValue = Double.NaN
    private var secondValue = Double.NaN
    private var currentOperation: String? = null
    private var isOperationPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn0 = findViewById(R.id.btn14)
        btn1 = findViewById(R.id.btn9)
        btn2 = findViewById(R.id.btn10)
        btn3 = findViewById(R.id.btn11)
        btn4 = findViewById(R.id.btn5)
        btn5 = findViewById(R.id.btn6)
        btn6 = findViewById(R.id.btn7)
        btn7 = findViewById(R.id.btn1)
        btn8 = findViewById(R.id.btn2)
        btn9 = findViewById(R.id.btn3)
        btnAdd = findViewById(R.id.btn12)
        btnSubtract = findViewById(R.id.btn8)
        btnMultiply = findViewById(R.id.btn4)
        btnDivide = findViewById(R.id.btn16)
        btnClear = findViewById(R.id.btn13)
        btnEqual = findViewById(R.id.btn15)

        val numberClickListener = View.OnClickListener { v ->
            val b = v as Button
            if (isOperationPressed) {
                txtCalc.setText(b.text)
                isOperationPressed = false
            } else {
                txtCalc.append(b.text)
            }
        }

        btn0.setOnClickListener(numberClickListener)
        btn1.setOnClickListener(numberClickListener)
        btn2.setOnClickListener(numberClickListener)
        btn3.setOnClickListener(numberClickListener)
        btn4.setOnClickListener(numberClickListener)
        btn5.setOnClickListener(numberClickListener)
        btn6.setOnClickListener(numberClickListener)
        btn7.setOnClickListener(numberClickListener)
        btn8.setOnClickListener(numberClickListener)
        btn9.setOnClickListener(numberClickListener)

        btnAdd.setOnClickListener { performOperation("+") }
        btnSubtract.setOnClickListener { performOperation("-") }
        btnMultiply.setOnClickListener { performOperation("x") }
        btnDivide.setOnClickListener { performOperation("÷") }

        btnClear.setOnClickListener {
            txtCalc.setText("0")
            firstValue = Double.NaN
            secondValue = Double.NaN
            currentOperation = null
            isOperationPressed = false
        }

        btnEqual.setOnClickListener {
            if (!firstValue.isNaN() && !TextUtils.isEmpty(txtCalc.text.toString())) {
                secondValue = txtCalc.text.toString().toDouble()
                val result = performCalculation(firstValue, secondValue, currentOperation)
                txtCalc.setText(result.toString())
                firstValue = result
                secondValue = Double.NaN
                currentOperation = null
                isOperationPressed = true
            }
        }
    }

    private fun performOperation(operation: String) {
        if (!TextUtils.isEmpty(txtCalc.text.toString())) {
            if (!firstValue.isNaN()) {
                secondValue = txtCalc.text.toString().toDouble()
                firstValue = performCalculation(firstValue, secondValue, currentOperation)
            } else {
                firstValue = txtCalc.text.toString().toDouble()
            }
            currentOperation = operation
            isOperationPressed = true
        }
    }

    private fun performCalculation(firstValue: Double, secondValue: Double, operation: String?): Double {
        return when (operation) {
            "+" -> firstValue + secondValue
            "-" -> firstValue - secondValue
            "x" -> firstValue * secondValue
            "÷" -> firstValue / secondValue
            else -> secondValue
        }
    }
}
