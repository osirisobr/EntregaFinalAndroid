package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm

import android.app.Application

class App: Application() {
    companion object{
        var peliculas: MutableList<Pelicula> = ArrayList()
    }
    override fun onCreate(){
        super.onCreate()
        val data = PeliculasDaoMockImpl()
         peliculas = data.getTodos()


    }
}