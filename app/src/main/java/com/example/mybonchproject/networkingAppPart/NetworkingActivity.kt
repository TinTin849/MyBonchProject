package com.example.mybonchproject.networkingAppPart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mybonchproject.R
import com.example.mybonchproject.helpersForWork.NetworkingRequests
import com.example.mybonchproject.helpersForWork.FragmentNavigator
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com"

class NetworkingActivity : AppCompatActivity(), FragmentNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_networking)

        //Запуск стартового фрагмента авторизации и проверка для корректной работы при повороте
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.networkContainer, MenuFragment())
                    .commit()
        }
    }

    //Переключение фрагментов с помощью интерфейса FragmentNavigator
    override fun changeFragment(type: Int) {
        when (type){
            1 -> supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.networkContainer, AlbumsFragment())
                    .commit()
            2 -> supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.networkContainer, CreatePostFragment())
                    .commit()
            3 -> supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.networkContainer, MenuFragment())
                    .commit()
            4 -> supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.networkContainer, UsersFragment())
                    .commit()
        }
    }
}

fun newApi(): NetworkingRequests = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetworkingRequests::class.java)
