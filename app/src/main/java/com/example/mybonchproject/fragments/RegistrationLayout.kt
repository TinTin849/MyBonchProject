package com.example.mybonchproject.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mybonchproject.FragmentNavigator
import com.example.mybonchproject.MainMenuActivity
import com.example.mybonchproject.R
import com.example.mybonchproject.databinding.FragmentRegistrationLayoutBinding

//Экран регистрации с нуля
class RegistrationLayout : Fragment() {
    //Подключение удобного интерфейса binding
    private var fragmentRegistrationLayout: FragmentRegistrationLayoutBinding? = null

    //Установка класса-навигатора для смены фрагментов
    private lateinit var fragmentNavigator: FragmentNavigator
    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentNavigator = context as FragmentNavigator
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentRegistrationLayoutBinding.bind(view)
        fragmentRegistrationLayout = binding

        //Переключение между регистрацией и авторизацией
        binding.textView3.setOnClickListener {
            fragmentNavigator.changeFragment(1)
        }

        //Кнопка запуска процесса регистрации
        binding.buttonRegistration1.setOnClickListener {
            var status = ""
            if (binding.editTextEmailAddress.text.isEmpty()){
                status += getString(R.string.emailWarningReg)
            }
            if (binding.editTextPersonName1.text.isEmpty()){
                if(status.isNotEmpty()) status += "\n"
                status += getString(R.string.nameWarningReg)
            }
            if (binding.editTextPersonSurname1.text.isEmpty()){
                if(status.isNotEmpty()) status += "\n"
                status += getString(R.string.surnameWarningReg)
            }
            if (binding.editTextPassword1.text.toString() != binding.editTextPassword2.text.toString()){
                if(status.isNotEmpty()) status += "\n"
                status += getString(R.string.passwordWarningReg)
            }

            if (status != "") {
                showDialog(status)
            } else activity?.let {
                val intent = Intent(it, MainMenuActivity::class.java)
                it.startActivity(intent)
            }
        }
    }

    //Функция вызова предупреждения о незаполненных строках
    private fun showDialog(status:String) {
        val bundle = Bundle()
        bundle.putString("correction", status)
        val dialogFragment = UncompletedLines()
        dialogFragment.arguments = bundle

        dialogFragment.show(childFragmentManager, "dialog_event")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentRegistrationLayout = null
    }
}