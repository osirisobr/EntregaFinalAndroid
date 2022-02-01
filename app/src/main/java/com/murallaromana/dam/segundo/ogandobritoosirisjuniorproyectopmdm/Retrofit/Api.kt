package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit

import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Pelicula

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface Api {


    @GET("movies")
    fun getPeliculas(@Header("Authorization")token: String): Call<List<Pelicula>>
    @POST("users/login")
    fun login(@Body user: Usuario): Call<Token>
    @POST("users/signup")
    fun signup(@Body user: Usuario): Call<Unit>


}