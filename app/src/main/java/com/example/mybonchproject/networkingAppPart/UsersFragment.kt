package com.example.mybonchproject.networkingAppPart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybonchproject.R
import com.example.mybonchproject.databinding.FragmentUsersBinding
import com.example.mybonchproject.helpersForWork.UsersRvAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class UsersFragment : Fragment() {
    private var fragmentUsersBinding: FragmentUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentUsersBinding.bind(view)
        fragmentUsersBinding = binding

        //Корутина для обработки интернет-запроса
        GlobalScope.launch(Dispatchers.IO) {
            val response = newApi().getUsers().awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()!!

                withContext(Dispatchers.Main) {
                    //Настройка RecyclerView
                    val adapter = UsersRvAdapter()
                    adapter.setData(data)
                    binding.usersRV.layoutManager = LinearLayoutManager(activity)
                    binding.usersRV.adapter = adapter
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentUsersBinding = null
    }
}