package com.example.mybonchproject.messagingAppPart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mybonchproject.R
import com.example.mybonchproject.helpersForWork.FragmentNavigator

//Основная активити-фундамент
class MainActivity : AppCompatActivity(), FragmentNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Запуск стартового фрагмента авторизации и проверка для корректной работы при повороте
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.mainActivityContainer, SigningPageFragment())
                    .commit()
        }
    }

    //Переключение фрагментов с помощью интерфейса FragmentNavigator
    override fun changeFragment(type: Int) {
        if (type == 0){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainActivityContainer, RegistrationPageFragment())
                    .commit()
        } else {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainActivityContainer, SigningPageFragment())
                    .commit()
        }
    }
}

