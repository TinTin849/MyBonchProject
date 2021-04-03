package com.example.mybonchproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.example.mybonchproject.databinding.ActivityMainMenuBinding
import kotlinx.parcelize.Parcelize

//Активити послеавторизационного периода
class MainMenuActivity : AppCompatActivity() {
    private var activityMainMenuBinding: ActivityMainMenuBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainMenuBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        //Получение данных авторизации
        val intent = intent
        val extra = intent.extras

        val message = extra?.getString("message")
        binding.mainActivityMessage.text = message

        val bigUser = extra?.getParcelable<User>("userInfo")
        val info = "E-mail: " + bigUser?.userEmail + "\n" + "Пароль: " + bigUser?.userPassword
        binding.userInfo.text = info
    }

    override fun onDestroy() {
        super.onDestroy()
        activityMainMenuBinding = null
    }
}

//Класс передачи данных о пользователе
@Parcelize
data class User(val userPassword: String?, val userEmail: String?): Parcelable