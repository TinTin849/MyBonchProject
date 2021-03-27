package com.example.mybonchproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.mybonchproject.R
import com.example.mybonchproject.databinding.FragmentUncompletedLinesBinding

class UncompletedLines : DialogFragment() {
    private var fragmentUncompletedLinesBinding: FragmentUncompletedLinesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_uncompleted_lines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentUncompletedLinesBinding.bind(view)
        fragmentUncompletedLinesBinding = binding

        binding.button.setOnClickListener {
            dismiss()
        }

        val bundle = arguments?.get("correction")
        binding.textView7.setText(bundle.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentUncompletedLinesBinding = null
    }
}