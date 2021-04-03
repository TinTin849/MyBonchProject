package com.example.mybonchproject.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mybonchproject.MainMenuActivity
import com.example.mybonchproject.R
import com.example.mybonchproject.User
import com.example.mybonchproject.databinding.FragmentSigninLayoutBinding

//Экран авторизации по паролю и почте
class SigningLayout : Fragment() {
    //Подключение удобного интерфейса binding
    private var fragmentSigningLayoutBinding: FragmentSigninLayoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signin_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSigninLayoutBinding.bind(view)
        fragmentSigningLayoutBinding = binding

        //Переключение между регистрацией и авторизацией
        binding.textView6.setOnClickListener {
            val fr = fragmentManager?.beginTransaction()
            fr?.replace(R.id.container, RegistrationLayout())
            fr?.commit()
        }

        //Кнопка запуска процесса входа
        binding.buttonEntering.setOnClickListener {
            var counter:Int = 0
            if (binding.editTextEmailAddress2.text.isEmpty()){
                counter++
            }
            if (binding.editTextPassword3.text.isEmpty()){
                counter += 2
            }

            when (counter) {
                1 -> showDialog(getString(R.string.emailWarningSign))
                2 -> showDialog(getString(R.string.passwordWarningSign))
                3 -> showDialog(getString(R.string.dataWarningSign))
                else -> activity?.let { val intent = Intent(it, MainMenuActivity::class.java)
                    intent.putExtra("message", getString(R.string.welcomeFromSign))
                    val pass = binding.editTextPassword3.text.toString()
                    val mail = binding.editTextEmailAddress2.text.toString()
                    intent.putExtra("userInfo", User(pass, mail))
                    it.startActivity(intent)
                }
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
        fragmentSigningLayoutBinding = null
    }
}

