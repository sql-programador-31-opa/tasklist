package com.opapruebas.tasklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.opapruebas.tasklist.databinding.ActivityUpdatetaskBinding


class updatetask : AppCompatActivity() {

    lateinit var binding: ActivityUpdatetaskBinding

    lateinit var room : DBMASTER

    lateinit var task: task

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityUpdatetaskBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        room = Room.databaseBuilder(this, DBMASTER::class.java,"DBMASTER").build()
        setContentView(binding.root)
        val bundle = intent.extras
        val taskb = bundle!!.getBundle("task")

        binding.btnactu.setOnClickListener{

        }

    }
    private fun gettask(task: task){
        this.task = task
        binding.etdescripcion.setText(this.task.descripcion)
        binding.ettitulo.setText(this.task.nombre)
        binding.progressbar.setProgress(this.task.progreso)
    }
}