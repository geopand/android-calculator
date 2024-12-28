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
    private var isSecondInputActive: Boolean = false
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
            isSecondInputActive = false
            clearInputScreen()
            clearResultScreen()
        }

        binding.btnPlus.setOnClickListener {
            if (isValidToEnterOperation()) {
                executeLastOperation()
                operation = ArithmeticOperation.ADDITION
                isSecondInputActive = true
                concatToInputScreen(" + ")
            }
        }

        binding.btnMinus.setOnClickListener {
            if (isValidToEnterOperation()) {
                executeLastOperation()
                operation = ArithmeticOperation.SUBSTRACTION
                isSecondInputActive = true
                concatToInputScreen(" - ")
            }
        }

        binding.btnMulti.setOnClickListener {
            if (isValidToEnterOperation()) {
                executeLastOperation()
                operation = ArithmeticOperation.MULTIPLICATION
                isSecondInputActive = true
                concatToInputScreen(" * ")
            }
        }

        binding.btnDivide.setOnClickListener {
            if (isValidToEnterOperation()) {
                executeLastOperation()
                operation = ArithmeticOperation.DIVISION
                isSecondInputActive = true
                concatToInputScreen(" / ")
            }
        }

        binding.btnEquals.setOnClickListener {
            input1 = calculateResult()
            input2 = "0"
            screenText = input1
            isSecondInputActive = false
            operation = ArithmeticOperation.NONE
        }


    }

    private fun executeLastOperation() {
        if (operation != ArithmeticOperation.NONE) {
            input1 = calculateResult()
            input2 = "0"
        }
    }

    private fun populateInputs(btnValue: String) {
        if (!isSecondInputActive) {
            input1 = concatOrSetIfZero(input1, btnValue)
            concatToInputScreen(btnValue)
        } else {
            input2 = concatOrSetIfZero(input2, btnValue)
            concatToInputScreen(btnValue)
        }
    }

    private fun isValidToEnterOperation() : Boolean {
        return !arrayOf("", "0").contains(input1)
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
        binding.tvResult.text = ""
    }

    private fun showInputScreen(text: String){
        binding.tvInput.text = text
    }

    /**
     * Execute the last stored operation
     * Clear the input text-view
     * Show the result to the result text-view
     */
    private fun calculateResult(): String {
        val resultStr: String = operation.apply(input1, input2)
        binding.tvInput.text = ""
        binding.tvResult.text = resultStr
        return resultStr
    }


    /**
     * If the string value is "0", this means that the previous input is zero so
     * the new number must replace the value.
     * If the string value is non-zero then this means the user wants to write a bigger number so we
     * we concatenate the previous value with the new value.
     */
    private fun concatOrSetIfZero(initial: String, newValue: String): String {
        return if (initial.trim() == "0") {
            newValue
        } else {
            initial + newValue
        }
    }


}