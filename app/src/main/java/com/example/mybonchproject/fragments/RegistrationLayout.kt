package com.example.mybonchproject.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mybonchproject.MainMenuActivity
import com.example.mybonchproject.R
import com.example.mybonchproject.databinding.FragmentRegistrationLayoutBinding

//Экран регистрации с нуля
class RegistrationLayout : Fragment() {
    //Подключение удобного интерфейса binding
    private var fragmentRegistrationLayout: FragmentRegistrationLayoutBinding? = null

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
            val fr = fragmentManager?.beginTransaction()
            fr?.replace(R.id.container, SigningLayout())
            fr?.commit()
        }

        //Кнопка запуска процесса регистрации
        binding.buttonRegistration1.setOnClickListener {
            var str = ""
            if (binding.editTextEmailAddress.text.isEmpty()){
                str += getString(R.string.emailWarningReg)
            }
            if (binding.editTextPersonName1.text.isEmpty()){
                if(str.isNotEmpty()) str += "\n"
                str += getString(R.string.nameWarningReg)
            }
            if (binding.editTextPersonSurname1.text.isEmpty()){
                if(str.isNotEmpty()) str += "\n"
                str += getString(R.string.surnameWarningReg)
            }

            if (binding.editTextPassword1.text.toString() != binding.editTextPassword2.text.toString()){
                if(str.isNotEmpty()) str += "\n"
                str += getString(R.string.passwordWarningReg)
            }

            if (str != "") {
                showDialog(str)
            } else activity?.let { val intent = Intent(it, MainMenuActivity::class.java)
                //intent.putExtra("message", getString(R.string.welcomeFromReg))
                //val pass = binding.editTextPassword1.text.toString()
                //val mail = binding.editTextEmailAddress.text.toString()
                //intent.putExtra("userInfo", User(pass, mail))
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