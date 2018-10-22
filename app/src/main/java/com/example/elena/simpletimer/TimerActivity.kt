package com.example.elena.simpletimer

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView

class TimerActivity : AppCompatActivity() {
    private var secondsPassed : Int = 0
    private var isButtonPressed = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val textView = findViewById<TextView>(R.id.text)
        val button = findViewById<Button>(R.id.button)

        savedInstanceState?.run {
            secondsPassed = getInt("SECONDS_PASSED")
            isButtonPressed = getBoolean("IS_BUTTON_PRESSED")
        }

        fun secondsTick() {
            secondsPassed += 1
            textView.text = numberToString(secondsPassed)
        }

        val timer = object: CountDownTimer(1000000 - secondsPassed.toLong() * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                secondsTick()
            }

            override fun onFinish() {
                secondsTick()
                button.text = "START"
                isButtonPressed = false
                secondsPassed = 0
            }
        }

        if(isButtonPressed) {
            button.text = "STOP"
            timer.start()
        } else {
            button.text = "START"
            if (secondsPassed != 0) {
                textView.text = numberToString(secondsPassed)
            }
        }

        button.setOnClickListener {
            if (isButtonPressed) {
                timer.cancel()
                timer.onFinish()
            } else {
                button.text = "STOP"
                isButtonPressed = true
                timer.start()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("SECONDS_PASSED", secondsPassed)
        outState.putBoolean("IS_BUTTON_PRESSED", isButtonPressed)
        super.onSaveInstanceState(outState)
    }

    private fun numberToString(number : Int) : String {
        val stringNumberBuilder = StringBuilder()

        val hundreds = (number - number % 100)
        val tens = (number % 100 - number % 100 % 10)
        val units = (number % 100 % 10)
        stringNumberBuilder.append(numbersConvert[hundreds])
        stringNumberBuilder.append(" ")
        if (tens < 20) {
            stringNumberBuilder.append(numbersConvert[tens + units])
        } else {
            stringNumberBuilder.append(numbersConvert[tens])
            stringNumberBuilder.append(" ")
            stringNumberBuilder.append(numbersConvert[units])
        }
        return stringNumberBuilder.toString()
    }

    private val numbersConvert : Map<Int, String> = mapOf(0 to "",
            1 to "один",
            2 to "два",
            3 to "три",
            4 to "четыре",
            5 to "пять",
            6 to "шесть",
            7 to "семь",
            8 to "восемь",
            9 to "девять",
            10 to "десять",
            11 to "одиннадцать",
            12 to "двенадцать",
            13 to "тринадцать",
            14 to "четырнадцать",
            15 to "пятнадцать",
            16 to "шестнадцать",
            17 to "семнадцать",
            18 to "восемнадцать",
            19 to "девятнадцать",
            20 to "двадцать",
            30 to "тридцать",
            40 to "сорок",
            50 to "пятьдесят",
            60 to "шестьдесят",
            70 to "семьдесят",
            80 to "восемьдесят",
            90 to "девяносто",
            100 to "сто",
            200 to "двести",
            300 to "триста",
            400 to "четыреста",
            500 to "пятьсот",
            600 to "шестьсот",
            700 to "семьсот",
            800 to "восемьсот",
            900 to "девятьсот",
            1000 to "тысяча")
}
