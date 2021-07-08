package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {


    var lastNumeric: Boolean = false
    var lastDot: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        // Toast.makeText(this, "Button works", Toast.LENGTH_SHORT).show()
        val tvInput = findViewById<TextView>(R.id.tvInput)
        tvInput.append((view as Button).text)
        // tvInput.append("1")
        // append - concatenation / adding to
        // view as Button - casting
        lastNumeric = true

        //if (tvInput.text.contains("1")) //
        // tvInput.text = "haha"

    }

    fun onClear(view: View) {
        //val buttonClear = findViewById<Button>(R.id.buttonClear)
        val tvInput = findViewById<TextView>(R.id.tvInput)
        tvInput.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {   // was and was not
            val tvInput = findViewById<TextView>(R.id.tvInput)
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
        /*else if (lastDot && !lastNumeric) {
            val tvInput = findViewById<TextView>(R.id.tvInput)
            tvInput.append("")
            lastNumeric = false
            lastDot = false
        }*/
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            val tvInput = findViewById<TextView>(R.id.tvInput)
            var tvValue = tvInput.text.toString()
            var preFix: String = ""

            try {
                if (tvValue.startsWith("-"))
                    preFix = "-"
                tvValue = tvValue.substring(1)
// -215 - use 215

                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")  //split by delimiters  99 - 1
                    // splits values on each side of the "-"
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    if (!preFix.isEmpty()) { //transfers"-" to one
                        one = preFix + one
                    }
// -10 - 5  - splits second minus into null and 10 - error
                    tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                } else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (!preFix.isEmpty()) {
                        one = preFix + one
                    }
                    tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                } else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (!preFix.isEmpty()) {
                        one = preFix + one
                    }
                    tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                } else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (!preFix.isEmpty()) {
                        one = preFix + one
                    }
                    tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace() /*
                It is a method of Java's throwable class which prints the throwable along with other
                details like the line number and class name where the exception occurred. ...
                For example, if one out of five methods in your code cause an exception, printStackTrace()
                will pinpoint the exact line in which the method raised the exception.*/
            }
        }
    }


    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length - 2)
        // 99.0
        // 0123
        return value
    }


    fun onOperator(view: View) {
        val tvInput = findViewById<TextView>(R.id.tvInput)
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())) {
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

private fun isOperatorAdded(value: String) : Boolean{  // if value is String returns Boolean (true or false)
    return if (value.startsWith("-")) {
        false
    }else{
        value.contains("/") || value.contains("*")
                || value.contains("+") || value.contains("-")
    }
}

}