package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Pelicula(

    @PrimaryKey(autoGenerate = true) @Transient var idRoom: String,

    @SerializedName("title")  var titulo: String,
    @SerializedName("rating")  var rating: String,
    @SerializedName("genre")  var genero: String,
    @SerializedName("directorFullname")  var director: String,
    @SerializedName("releaseYear")  var año: String,
    @SerializedName("imageUrl")  var url: String,
    @SerializedName("description")  var sinopsis: String,
    @SerializedName("runtimeMinutes")  var duracion: String,
    @SerializedName("directorPhone")  var numeroDirector: String,
    @SerializedName("id")  var id: String?,
  //  @SerializedName("title")  var imagenPanoramica: String

): Serializable {

//

    fun getGeneroPelicula(): String {
        return genero
    }

    fun getDirectorPelicula(): String {

        return director
    }

    fun getsinopsis(): String {

        return sinopsis
    }

    /*fun imagenPanoramica(): String{

       return imagenPanoramica

    }*/



}