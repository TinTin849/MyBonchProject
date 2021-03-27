package com.example.mybonchproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.example.mybonchproject.fragments.RegistrationLayout
import com.example.mybonchproject.fragments.SigningLayout
import kotlinx.parcelize.Parcelize

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signingLayout = SigningLayout()
        val registrationLayout = RegistrationLayout()

        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, signingLayout)
            commit()
        }
    }
}

