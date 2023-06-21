package com.example.calculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var finalresult: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.expressionArea)
        finalresult= findViewById(R.id.textArea)

        val button0: Button = findViewById(R.id.zero)
        val button1: Button = findViewById(R.id.one)
        val button2: Button = findViewById(R.id.two)
        val button3: Button = findViewById(R.id.three)
        val button4: Button = findViewById(R.id.four)
        val button5: Button = findViewById(R.id.five)
        val button6: Button = findViewById(R.id.six)
        val button7: Button = findViewById(R.id.seven)
        val button8: Button = findViewById(R.id.eight)
        val button9: Button = findViewById(R.id.nine)
        val buttonDot: Button = findViewById(R.id.point)

        val buttonPlus: Button = findViewById(R.id.add)
        val buttonMinus: Button = findViewById(R.id.subtract)
        val buttonMultiply: Button = findViewById(R.id.multiply)
        val buttonDivide: Button = findViewById(R.id.divide)
        val buttonEquals: Button = findViewById(R.id.equals)
        val buttonClear: Button = findViewById(R.id.clear)

        val buttonNegation: Button = findViewById(R.id.negative)

        val buttonBackspace: Button = findViewById(R.id.delete)


        button0.setOnClickListener { appendNumber("0") }
        button1.setOnClickListener { appendNumber("1") }
        button2.setOnClickListener { appendNumber("2") }
        button3.setOnClickListener { appendNumber("3") }
        button4.setOnClickListener { appendNumber("4") }
        button5.setOnClickListener { appendNumber("5") }
        button6.setOnClickListener { appendNumber("6") }
        button7.setOnClickListener { appendNumber("7") }
        button8.setOnClickListener { appendNumber("8") }
        button9.setOnClickListener { appendNumber("9") }
        buttonDot.setOnClickListener { appendNumber(".") }

        buttonPlus.setOnClickListener {
            val currentText = resultTextView.text.toString()
            resultTextView.text = currentText + "+"
        }

        buttonMinus.setOnClickListener {
            val currentText = resultTextView.text.toString()
            resultTextView.text = currentText + "-"
        }
        buttonDivide.setOnClickListener {
            val currentText = resultTextView.text.toString()
            resultTextView.text = currentText + "/"
        }
        buttonMultiply.setOnClickListener {
            val currentText = resultTextView.text.toString()
            resultTextView.text = currentText + "*"
        }
        buttonClear.setOnClickListener {
            finalresult.text = " "
        }

        buttonEquals.setOnClickListener {
            evaluateExpression()
        }

        buttonBackspace.setOnClickListener{
            val currentText = resultTextView.text.toString()
            val modifiedString = deleteLastCharacter(currentText)
            resultTextView.text=modifiedString
        }

        buttonNegation.setOnClickListener{
            val currentText = resultTextView.text.toString()
            val modifiedString = negationCharacter(currentText)
            resultTextView.text=modifiedString
        }
    }

    fun deleteLastCharacter(input: String): String {
        if (input.isEmpty()) {
            return ""
        } else {
            return input.substring(0, input.length - 1)
        }
    }

    fun negationCharacter(input: String): String {
        return if (input.startsWith("-")) {
            input.substring(1)
        } else {
            "-$input"
        }
    }

    private fun evaluateExpression() {
        val expressionString = resultTextView.text.toString()

        try {
            val expression = ExpressionBuilder(expressionString).build()
            val result = expression.evaluate()
            finalresult.text = result.toString()
        } catch (e: Exception) {
            finalresult.text = "Invalid Expression"
            e.printStackTrace()
        }
}
    private fun appendNumber(number: String) {
        val currentText = resultTextView.text.toString()
        resultTextView.text = currentText + number
    }
}