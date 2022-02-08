package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Pelicula
import retrofit2.Call
import retrofit2.http.*


interface Api {
   var pelicula: Pelicula



    @GET("movies")
    fun getPeliculas(@Header("Authorization")token: String): Call<List<Pelicula>>
    @GET("movies")
    fun getId(@Header("Authorization")token: String): Call<List<Pelicula>>

    //En proceso
    @PUT("movies/")
    fun update(@Header("Authorization")token: String,@Path("")path: String ): Call<Pelicula>




    @POST("users/login")
    fun login(@Body user: Usuario): Call<Token>
    @POST("users/signup")
    fun signup(@Body user: Usuario): Call<Unit>
    @POST("movies/")
    fun create(@Body pelicula: Pelicula, @Header("Authorization")token: String ): Call<Unit>

}