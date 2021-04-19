package com.example.mybonchproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mybonchproject.fragments.RegistrationLayout
import com.example.mybonchproject.fragments.SigningLayout

//Основная активити-фундамент
class MainActivity : AppCompatActivity(), FragmentNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Запуск стартового фрагмента авторизации и проверка для корректной работы при повороте
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, SigningLayout())
                    .commit()
        }
    }

    //Переключение фрагментов с помощью интерфейса FragmentNavigator
    override fun changeFragment(type: Int) {
        if (type == 0){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, RegistrationLayout())
                    .commit()
        } else {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, SigningLayout())
                    .commit()
        }
    }
}

