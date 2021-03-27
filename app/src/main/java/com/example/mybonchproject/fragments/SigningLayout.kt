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
import com.example.mybonchproject.databinding.FragmentSigninLayoutBinding
import kotlinx.parcelize.Parcelize

class SigningLayout : Fragment() {
    private var fragmentSigningLayoutBinding: FragmentSigninLayoutBinding? = null
    private val welcome = "Можно простить уход, но как простить возвращение?"

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

        binding.textView6.setOnClickListener {
            val fr = fragmentManager?.beginTransaction()
            fr?.replace(R.id.container, RegistrationLayout())
            fr?.commit()
        }

        binding.buttonEntering.setOnClickListener {
            var counter:Int = 0
            if (binding.editTextEmailAddress2.text.isEmpty()){
                counter++
            }
            if (binding.editTextPassword3.text.isEmpty()){
                counter += 2
            }

            when (counter) {
                1 -> showDialog("Введите электронную почту!")
                2 -> showDialog("Введите пароль!")
                3 -> showDialog("Введите данные!")
                else -> activity?.let { val intent = Intent(it, MainMenuActivity::class.java)
                    intent.putExtra("message", welcome)
                    val pass = binding.editTextPassword3.text.toString()
                    val mail = binding.editTextEmailAddress2.text.toString()
                    intent.putExtra("userInfo", User(pass, mail))
                    it.startActivity(intent)
                }
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
        fragmentSigningLayoutBinding = null
    }
}

