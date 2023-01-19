package com.opapruebas.tasklist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
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
    }

    fun getTask(room:DBMASTER){
            lifecycleScope.launch{
            listaTask = room.daoTask().getTask()
            adaptador = adaptadorTask(listaTask, this@MainActivity)
            binding.tasklist.adapter = adaptador

        }
    }

    override fun onEditItemClick(task: task) {
        val builder = AlertDialog.Builder(this@MainActivity)
        val view = layoutInflater.inflate(R.layout.activity_addtask, null)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
    }

    override fun onDeleteItemClick(task: task) {
        lifecycleScope.launch{
        room.daoTask().deleteTask(task.id)
        adaptador.notifyDataSetChanged()
            getTask(room)
        }
    }

}






