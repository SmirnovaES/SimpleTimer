package com.example.elena.simpletimer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class StartActivity : AppCompatActivity() {

    private lateinit var timer : CountDownTimer
    private var isStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        savedInstanceState?.run {
            isStarted = getBoolean("IS_STARTED")
        }

        if (!isStarted) {
            isStarted = true
            val intent = Intent(this, TimerActivity::class.java)
            timer = object: CountDownTimer(2000, 2000) {
                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {
                    startActivity(intent)
                }
            }
            timer.start()
        }
    }

    override fun onBackPressed() {
        if (isStarted && ::timer.isInitialized) {
            timer.cancel()
        }
        super.onBackPressed()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("IS_STARTED", isStarted)
        super.onSaveInstanceState(outState)
    }
}
