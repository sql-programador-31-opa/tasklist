package com.opapruebas.tasklist

interface AdaptadorListener {

    fun onEditItemClick(task: task)
    fun onDeleteItemClick(task: task)

}
