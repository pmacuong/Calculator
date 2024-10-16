package com.example.calculatorcontraint

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: EditText

    private var firstNum = ""
    private var secondNum = ""
    private var operation = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        val buttons = arrayOf(
            findViewById<Button>(R.id.button0),
            findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5),
            findViewById<Button>(R.id.button6),
            findViewById<Button>(R.id.button7),
            findViewById<Button>(R.id.button8),
            findViewById<Button>(R.id.button9),
            findViewById<Button>(R.id.buttonPlus),
            findViewById<Button>(R.id.buttonMinus),
            findViewById<Button>(R.id.buttonMultiply),
            findViewById<Button>(R.id.button_factor),
            findViewById<Button>(R.id.buttonEqual)
        )

        for (button in buttons) {
            button.setOnClickListener(this::onButtonClick)
        }

        findViewById<Button>(R.id.button_C).setOnClickListener {
            firstNum = ""
            secondNum = ""
            operation = ""
            display.setText("")
        }

        findViewById<Button>(R.id.button_CE).setOnClickListener {
            firstNum = ""
            secondNum = ""
            operation = ""
            display.setText("")
        }

        findViewById<Button>(R.id.buttonBS).setOnClickListener {
            val text = display.text.toString()
            if (text.isNotEmpty()) {
                display.setText(text.substring(0, text.length - 1))
            }
        }
    }

    private fun onButtonClick(view: View) {
        when (view.id) {
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9 -> {
                val num = (view as Button).text.toString()
                if (operation.isEmpty()) {
                    firstNum += num
                    display.setText(firstNum)
                } else {
                    secondNum += num
                    display.setText(secondNum)
                }
            }
            R.id.buttonPlus, R.id.buttonMinus, R.id.buttonMultiply, R.id.button_factor -> {
                operation = (view as Button).text.toString()
            }
            R.id.buttonEqual -> {
                if (firstNum.isNotEmpty() && secondNum.isNotEmpty()) {
                    val result = when (operation) {
                        "+" -> firstNum.toInt() + secondNum.toInt()
                        "-" -> firstNum.toInt() - secondNum.toInt()
                        "x" -> firstNum.toInt() * secondNum.toInt()
                        "/" -> firstNum.toInt() / secondNum.toInt()
                        else -> 0
                    }
                    display.setText(result.toString())
                    firstNum = ""
                    secondNum = ""
                    operation = ""
                }
            }
        }
    }
}