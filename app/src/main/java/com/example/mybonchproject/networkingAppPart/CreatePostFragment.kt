package com.example.mybonchproject.networkingAppPart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybonchproject.R
import com.example.mybonchproject.databinding.FragmentCreatePostBinding
import com.example.mybonchproject.helpersForWork.AlbumsRvAdapter
import com.example.mybonchproject.helpersForWork.CreatePostDataClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

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

        binding.buttonPost.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val body = binding.editTextBody.text.toString()
            val enteredData = CreatePostDataClass(title, body)
            Log.d("MyTest", title)

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
                            Log.d("MyTest", "my test OK")
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