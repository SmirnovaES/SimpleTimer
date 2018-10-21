package com.example.elena.simpletimer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val intent = Intent(this, TimerActivity::class.java)
        val timer = object: CountDownTimer(2000, 2000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                startActivity(intent)
            }
        }

        timer.start()
    }
}
