package com.example.mybonchproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mybonchproject.databinding.ActivityMainMenuBinding

//Активити чата
class MainMenuActivity : AppCompatActivity() {
    private var activityMainMenuBinding: ActivityMainMenuBinding? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivityMainMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val mainViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        //Настройка RecyclerView
        val adapter = RvAdapter()
        binding.RV.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.RV.adapter = adapter

        val botArr = resources.getStringArray(R.array.botMessages)

        //Релизация отправки сообщения
        binding.sendMessageImView.setOnClickListener() {
            if (binding.messageEditText.text.isNotEmpty()) {
                val mes = binding.messageEditText.text.toString()
                //Очистка поля ввода
                binding.messageEditText.setText("")
                //Log.d("testing", "Message is $mes")
                mainViewModel.addingOfMes(mes)
                //Log.d("testing", "written mes is ${mainViewModel.viewState.value?.userMessage}")
            }
        }

        mainViewModel.viewState.observe(this, Observer{
            //Log.d("testing", "observerOK and ${it.workArr}")

            adapter.setData(it.workArr)
            binding.RV.scrollToPosition(adapter.itemCount - 1)

            //Задержка отправки ответного сообщения
            if (it.workArr.size%2 != 0) {
                val handler = Handler()
                handler.postDelayed({
                    it.workArr.add(it.workArr.size, botArr[it.numOfAnswer])

                    //Передача информации в список и скролл вниз до новых элементов
                    adapter.setData(it.workArr)
                    binding.RV.scrollToPosition(adapter.itemCount - 1)

                    //Обеспечение цикличных ответов бота
                    mainViewModel.numOfAnswerRealisation()
                }, 600)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        activityMainMenuBinding = null
    }
}