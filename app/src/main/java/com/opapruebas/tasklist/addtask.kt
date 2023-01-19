package com.opapruebas.tasklist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.opapruebas.tasklist.databinding.ActivityAddtaskBinding
import kotlinx.coroutines.launch

class addtask : AppCompatActivity() {
    lateinit var binding: ActivityAddtaskBinding

    lateinit var room : DBMASTER

    lateinit var task: task
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddtaskBinding.inflate(layoutInflater)
        room = Room.databaseBuilder(this, DBMASTER::class.java,"DBMASTER").build()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnagregartarea.setOnClickListener(){
            task = task(
                binding.tetitulo.text.toString(),
                binding.tedescripcion.text.toString()
            )
            createTask(room,task)
            startActivity(Intent(this, MainActivity::class.java ))

        }

    }
    fun createTask(room:DBMASTER,task: task) {
        lifecycleScope.launch {
            room.daoTask().addTask(task)

        }
    }
}