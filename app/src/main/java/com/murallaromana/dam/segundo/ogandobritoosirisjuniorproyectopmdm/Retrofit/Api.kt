package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Pelicula
import retrofit2.Call
import retrofit2.http.*


interface Api {
   var pelicula: Pelicula

 //@Path("")path: String,

    @GET("movies")
    fun getPeliculas(@Header("Authorization")token: String): Call<List<Pelicula>>
    @GET("movies/{id}")
    fun getId(@Header("Authorization")token: String, @Path("id")id:String?): Call<Pelicula>

    //En proceso
    @PUT("movies/")
    fun update(@Body pelicula: Pelicula, @Header("Authorization")token: String): Call<Unit>




    @POST("users/login")
    fun login(@Body user: Usuario): Call<Token>
    @POST("users/signup")
    fun signup(@Body user: Usuario): Call<Unit>
    @POST("movies/")
    fun create(@Body pelicula: Pelicula, @Header("Authorization")token: String ): Call<Unit>

}