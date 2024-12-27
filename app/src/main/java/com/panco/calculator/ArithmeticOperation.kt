package com.panco.calculator

import java.util.function.BiFunction

enum class ArithmeticOperation : BiFunction<String, String, String>{

    NONE {
        override fun apply(var1: String, var2: String): String {
            //do nothing
            return ""
        }
    }, ADDITION {
        override fun apply(var1: String, var2: String): String {
            val d1: Double = var1.toDouble()
            val d2: Double = var2.toDouble()
            val d3: Double = d1 + d2
            return d3.toString()
        }
    }, SUBSTRACTION {
        override fun apply(var1: String, var2: String): String {
            val d1: Double = var1.toDouble()
            val d2: Double = var2.toDouble()
            val d3: Double = d1 - d2
            return d3.toString()
        }
    }, MULTIPLICATION {
        override fun apply(var1: String, var2: String): String {
            val d1: Double = var1.toDouble()
            val d2: Double = var2.toDouble()
            val d3: Double = d1 * d2
            return d3.toString()
        }
    }, DIVISION {
        override fun apply(var1: String, var2: String): String {
            val d1: Double = var1.toDouble()
            val d2: Double = var2.toDouble()
            if (d2.toInt() == 0) {
                return "Division by zero"
            }
            val d3: Double = d1 / d2
            return d3.toString()
        }
    }
}