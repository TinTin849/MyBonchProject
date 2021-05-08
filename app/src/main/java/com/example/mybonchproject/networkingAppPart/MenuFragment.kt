package com.example.mybonchproject.networkingAppPart

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mybonchproject.R
import com.example.mybonchproject.databinding.FragmentMenuBinding
import com.example.mybonchproject.helpersForWork.FragmentNavigator

class MenuFragment : Fragment() {
    private var fragmentMenuFragment: FragmentMenuBinding? = null

    //Установка класса-навигатора для смены фрагментов
    private lateinit var fragmentNavigator: FragmentNavigator
    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentNavigator = context as FragmentNavigator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMenuBinding.bind(view)
        fragmentMenuFragment = binding

        binding.albumsButton.setOnClickListener{
            fragmentNavigator.changeFragment(1)
        }

        binding.usersButton.setOnClickListener{
            fragmentNavigator.changeFragment(4)
        }

        binding.newPostButton.setOnClickListener{
            fragmentNavigator.changeFragment(2)
        }
    }
}