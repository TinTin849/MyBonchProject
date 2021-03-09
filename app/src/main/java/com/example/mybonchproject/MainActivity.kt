package com.example.mybonchproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.OnClickAction
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View){
        //написать сюда переход на вход
        val intent = Intent(this, SigningLayout::class.java)
        startActivity(intent)
    }
}