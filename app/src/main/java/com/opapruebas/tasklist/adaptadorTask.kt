package com.opapruebas.tasklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class adaptadorTask (
    val listaTask: MutableList<task>,
    val listener: AdaptadorListener
):RecyclerView.Adapter<adaptadorTask.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_rvitemtarea,parent,false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val task = listaTask[position]
       holder.lbdescripcion.text = task.descripcion
       holder.lvprogress.progress = task.progreso
        holder.cronotimercount.base = task.fechat
        val progreso = task.progreso.toString()
        holder.tvprogreso.text = "$progreso %"
        holder.cvrv.setOnClickListener {
            listener.onEditItemClick(task)
        }

        holder.btnborrar.setOnClickListener {
            listener.onDeleteItemClick(task)
        }

    }

    override fun getItemCount(): Int {
        return listaTask.size
    }

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val cvrv = itemView.findViewById<CardView>(R.id.cvrv)
        val lbdescripcion = itemView.findViewById<TextView>(R.id.lbdescripcion)
        val lvprogress = itemView.findViewById<ProgressBar>(R.id.progressBar3)
        val cronotimercount= itemView.findViewById<Chronometer>(R.id.cronotimercount)
        val btnborrar = itemView.findViewById<Button>(R.id.btnborrar)
        val tvprogreso =itemView.findViewById<TextView>(R.id.tvprogreso)
    }
}