package com.example.mybonchproject.messagingAppPart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.mybonchproject.R
import com.example.mybonchproject.databinding.UncompletedLinesDialogBinding

//Диалог уведомления о незаполненных строках
class UncompletedLinesDialogFragment : DialogFragment() {
    private var uncompletedLinesDialogBinding: UncompletedLinesDialogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.uncompleted_lines_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = UncompletedLinesDialogBinding.bind(view)
        uncompletedLinesDialogBinding = binding

        //Кнопка закрытия уведомления
        binding.button.setOnClickListener {
            dismiss()
        }

        val bundle = arguments?.get("correction")
        binding.textView7.text = bundle.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        uncompletedLinesDialogBinding = null
    }
}