package com.example.mybonchproject.networkingAppPart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mybonchproject.R
import com.example.mybonchproject.databinding.FragmentCreatePostBinding
import com.example.mybonchproject.helpersForWork.CreatePostDataClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePostFragment : Fragment() {
    private var fragmentCreatePostBinding: FragmentCreatePostBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCreatePostBinding.bind(view)
        fragmentCreatePostBinding = binding

        //Активация запроса по нажатию на кнопку
        binding.buttonPost.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val body = binding.editTextBody.text.toString()
            val enteredData = CreatePostDataClass(title, body)

            newApi().postCreatePost(enteredData).enqueue(
                object : Callback<CreatePostDataClass> {
                    override fun onFailure(call: Call<CreatePostDataClass>, t: Throwable) {
                        Toast.makeText(activity, "Not Good", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(
                        call: Call<CreatePostDataClass>,
                        response: Response<CreatePostDataClass>
                    ) {
                        val returnedData = response.body()
                        val answer: String = returnedData?.title.toString() + " " + returnedData?.body.toString() + " " + returnedData?.userId.toString()
                        Toast.makeText(activity, answer, Toast.LENGTH_LONG).show()
                    }
                }
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentCreatePostBinding = null
    }
}