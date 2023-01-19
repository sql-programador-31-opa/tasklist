package com.opapruebas.tasklist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.opapruebas.tasklist.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(),AdaptadorListener {

    lateinit var binding : ActivityMainBinding

    var listaTask: MutableList<task> = mutableListOf()

    lateinit var adaptador: adaptadorTask

    lateinit var room : DBMASTER

    lateinit var task: task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        room = Room.databaseBuilder(this, DBMASTER::class.java,"DBMASTER").build()
        getTask(room)
        binding.agregartarea.setOnClickListener(){
            startActivity(Intent(this, addtask::class.java ))
        }
        binding.btnactu.setOnClickListener(){
            if (binding.ettitulo.text.isNullOrEmpty() || binding.etdescripcion.text.isNullOrEmpty()){
                Toast.makeText(this, "llena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                task.descripcion = binding.etdescripcion.text.toString().trim()
                task.nombre = binding.ettitulo.text.toString().trim()
                task.progreso = binding.progressbar.progress
                updatetask(room,task)
            }
        }
        binding.btncancel.setOnClickListener(){
            binding.cardedit.visibility = View.INVISIBLE
        }
    }

    fun getTask(room:DBMASTER){
            lifecycleScope.launch{
            listaTask = room.daoTask().getTask()
            adaptador = adaptadorTask(listaTask, this@MainActivity)
            binding.tasklist.adapter = adaptador

        }
    }
    fun updatetask(room: DBMASTER,task: task){
    lifecycleScope.launch {
        room.daoTask().updateTask(task.id,task.nombre, task.descripcion,task.fechat,task.progreso)
        adaptador.notifyDataSetChanged()
        getTask(room)
        clearcampos()
        binding.cardedit.visibility = View.INVISIBLE
    }
    }

    private fun clearcampos() {
        binding.etdescripcion.setText("")
        binding.ettitulo.setText("")
        binding.progressbar.setProgress(0)

    }

    override fun onEditItemClick(task: task) {
        this.task = task
        binding.cardedit.visibility = View.VISIBLE
        binding.ettitulo.setText(this.task.nombre)
        binding.etdescripcion.setText(this.task.descripcion)
        binding.progressbar.setProgress(this.task.progreso)
   }

    override fun onDeleteItemClick(task: task) {
        lifecycleScope.launch{
        room.daoTask().deleteTask(task.id)
        adaptador.notifyDataSetChanged()
            getTask(room)
        }
    }

}






