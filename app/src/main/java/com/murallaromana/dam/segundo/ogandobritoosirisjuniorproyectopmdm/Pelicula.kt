package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pelicula(

    @SerializedName("title")  var titulo: String,
    var genero: String,
    var director: String,
    var año: String,
    var url: String,
    var sinopsis: String,
    var imagenPanoramica: String

): Serializable {

    fun getTituloPelicula(): String {
        return titulo + " " + año
    }

    fun getGeneroPelicula(): String {
        return genero
    }

    fun getDirectorPelicula(): String {

        return director
    }

    fun getsinopsis(): String {

        return sinopsis
    }

    fun imagenPanoramica(): String{

       return imagenPanoramica

    }



}