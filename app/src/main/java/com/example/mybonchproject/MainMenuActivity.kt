package com.example.mybonchproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.os.ResultReceiver
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        var numOfAnswer = 0
        var positionInWorkArr = 0
        val arr = arrayListOf("Первый ответ", "Второй ответ", "Третий ответ", "Четвёртый ответ", "Ответный ответ", "Шутливый ответ", "Усталый ответ", "Грустный ответ", "Игнорирование", "Пользователь заблокирован")
        val workArr = arrayListOf<String>()

        val adapter = RvAdapter()
        binding.RV.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        binding.RV.adapter = adapter

        binding.sendMessageImView.setOnClickListener(){
            val userMessage = binding.messageEditText.text.toString()

            binding.messageEditText.setText("")

            workArr.add(positionInWorkArr, userMessage)
            workArr.add(positionInWorkArr+1, arr[numOfAnswer])

            adapter.setData(workArr)

            if (numOfAnswer == 9) numOfAnswer = 0 else numOfAnswer++
            positionInWorkArr += 2
        }

/*
        //Получение данных авторизации
        val intent = intent
        val extra = intent.extras

        val message = extra?.getString("message")


        val bigUser = extra?.getParcelable<User>("userInfo")
        val info = "E-mail: " + bigUser?.userEmail + "\n" + "Пароль: " + bigUser?.userPassword
*/

    }

    override fun onDestroy() {
        super.onDestroy()
        activityMainMenuBinding = null
    }
}

//Класс передачи данных о пользователе
@Parcelize
data class User(val userPassword: String?, val userEmail: String?): Parcelable