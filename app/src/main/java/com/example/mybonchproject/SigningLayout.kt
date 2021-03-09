package com.example.mybonchproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SigningLayout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin_layout)
    }

    fun onClick(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}