package com.panco.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.panco.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var input1: String = ""
    private var input2: String = ""
    private var operation: ArithmeticOperation = ArithmeticOperation.NONE
    private var isFirstInputFilled: Boolean = false
    private var screenText: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        setContentView(binding.root)
        binding.btn0.setOnClickListener {
            populateInputs("0")
        }

        binding.btn1.setOnClickListener {
            populateInputs("1")
        }

        binding.btn2.setOnClickListener {
            populateInputs("2")
        }

        binding.btn3.setOnClickListener {
            populateInputs("3")
        }

        binding.btn4.setOnClickListener {
            populateInputs("4")
        }

        binding.btn5.setOnClickListener {
            populateInputs("5")
        }

        binding.btn6.setOnClickListener {
            populateInputs("6")
        }

        binding.btn7.setOnClickListener {
            populateInputs("7")
        }

        binding.btn8.setOnClickListener {
            populateInputs("8")
        }

        binding.btn9.setOnClickListener {
            populateInputs("9")
        }

        binding.btnDecimal.setOnClickListener {
            populateInputs(".")
        }

        binding.btnCE.setOnClickListener {
            input1 = "0"
            input2 = "0"
            isFirstInputFilled = false
            clearInputScreen()
            clearResultScreen()
        }

        binding.btnPlus.setOnClickListener {
            if (operation != ArithmeticOperation.NONE) {
                calculateResult()
                input1 = calculateResult()
                input2 = "0"
            }
            operation = ArithmeticOperation.ADDITION
            isFirstInputFilled = true
            concatToInputScreen(" + ")
        }

        binding.btnMinus.setOnClickListener {
            if (operation != ArithmeticOperation.NONE) {
                calculateResult()
                input1 = calculateResult()
                input2 = "0"
            }
            operation = ArithmeticOperation.SUBSTRACTION
            isFirstInputFilled = true
            concatToInputScreen(" - ")
        }

        binding.btnMulti.setOnClickListener {
            if (operation != ArithmeticOperation.NONE) {
                calculateResult()
                input1 = calculateResult()
                input2 = "0"
            }
            operation = ArithmeticOperation.MULTIPLICATION
            isFirstInputFilled = true
            concatToInputScreen(" * ")
        }

        binding.btnDivide.setOnClickListener {
            if (operation != ArithmeticOperation.NONE) {
                calculateResult()
                input1 = calculateResult()
                input2 = "0"
            }
            operation = ArithmeticOperation.DIVISION
            isFirstInputFilled = true
            concatToInputScreen(" / ")
        }

        binding.btnEquals.setOnClickListener {
            input1 = calculateResult()
            input2 = "0"
            screenText = input1
            operation = ArithmeticOperation.NONE
        }


    }

    private fun populateInputs(btnValue: String) {
        if (!isFirstInputFilled) {
            input1 = concatOrSetIfZero(input1, btnValue)
            concatToInputScreen(btnValue)
        } else {
            input2 = concatOrSetIfZero(input2, btnValue)
            concatToInputScreen(btnValue)
        }
    }


    private fun concatToInputScreen(text: String) {
        screenText += text
        showInputScreen(screenText)
    }

    private fun clearInputScreen() {
        screenText = ""
        showInputScreen("")
    }

    private fun clearResultScreen() {
        showResultScreen("")
    }

    private fun showInputScreen(text: String){
        binding.tvInput.text = text
    }

    private fun showResultScreen(text: String){
        binding.tvResult.text = text
    }


    private fun calculateResult(): String {
        val resultStr: String = operation.apply(input1, input2)
        binding.tvInput.text = ""
        binding.tvResult.text = resultStr
        return resultStr
    }


    private fun concatOrSetIfZero(initial: String, newValue: String): String {
        return if (initial.trim() == "0") {
            newValue
        } else {
            initial + newValue
        }
    }


}