package com.opapruebas.tasklist

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.opapruebas.tasklist.databinding.ActivityAddtaskBinding

class adddialogo(
    private val onSubmitClickListener:(task)->Unit

):DialogFragment() {

    lateinit var task: task

    private lateinit var binding: ActivityAddtaskBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = ActivityAddtaskBinding.inflate(LayoutInflater.from(context))
        var builder = AlertDialog.Builder(requireActivity())
        binding.btnagregartarea.setOnClickListener{
            task = task(
                 binding.tetitulo.text.toString().trim(),
                 binding.tedescripcion.text.toString().trim()
            )

            onSubmitClickListener.invoke(task)
        }
        val dialog = builder.create()
        return dialog
    }

}



