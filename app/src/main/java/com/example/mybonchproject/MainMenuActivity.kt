package com.example.mybonchproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mybonchproject.databinding.ActivityMainMenuBinding

//Активити чата
class MainMenuActivity : AppCompatActivity() {
    private var activityMainMenuBinding: ActivityMainMenuBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Номер сообщения бота
        var numOfAnswer = 0
        //Общая нумерация сообщений
        var positionInWorkArr = 0
        //Массив ответов бота
        val arr = resources.getStringArray(R.array.botMessages)
        //Общий массив сообщений
        val workArr = arrayListOf<String>()

        //Настройка RecyclerView
        val adapter = RvAdapter()
        binding.RV.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.RV.adapter = adapter

        //Релизация отправки сообщения
        binding.sendMessageImView.setOnClickListener(){
            val userMessage = binding.messageEditText.text.toString()

            //Очистка поля ввода
            binding.messageEditText.setText("")

            //Добавление сообщения в массив + добавление ответа от бота
            workArr.add(positionInWorkArr, userMessage)
            workArr.add(positionInWorkArr+1, arr[numOfAnswer])

            //Передача информации в список
            adapter.setData(workArr)

            //Обеспечение цикличных ответов бота
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
/*
//Класс передачи данных о пользователе
@Parcelize
data class User(val userPassword: String?, val userEmail: String?): Parcelable
*/