package com.example.mybonchproject.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mybonchproject.MainMenuActivity
import com.example.mybonchproject.R
import com.example.mybonchproject.User
import com.example.mybonchproject.databinding.FragmentRegistrationLayoutBinding
import kotlinx.parcelize.Parcelize


class RegistrationLayout : Fragment() {
    private var fragmentRegistrationLayout: FragmentRegistrationLayoutBinding? = null
    private val welcome = "Вы успешно внесены в Тетрадь смерти!"

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

        binding.textView3.setOnClickListener {
            val fr = fragmentManager?.beginTransaction()
            fr?.replace(R.id.container, SigningLayout())
            fr?.commit()
        }

        binding.buttonRegistration1.setOnClickListener {
            var str = ""
            if (binding.editTextEmailAddress.text.isEmpty()){
                str += "Введите адрес электронной почты"
            }
            if (binding.editTextPersonName1.text.isEmpty()){
                if(str.isNotEmpty()) str += "\n"
                str += "Введите имя"
            }
            if (binding.editTextPersonSurname1.text.isEmpty()){
                if(str.isNotEmpty()) str += "\n"
                str += "Введите фамилию"
            }

            if (binding.editTextPassword1.text.toString() != binding.editTextPassword2.text.toString()){
                if(str.isNotEmpty()) str += "\n"
                str += "Пароли должны совпадать"
            }

            if (str != "") {
                showDialog(str)
            } else activity?.let { val intent = Intent(it, MainMenuActivity::class.java)
                intent.putExtra("message", welcome)
                val pass = binding.editTextPassword1.text.toString()
                val mail = binding.editTextEmailAddress.text.toString()
                intent.putExtra("userInfo", User(pass, mail))
                it.startActivity(intent)
            }
        }
    }

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