package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofictClient {
    private fun getRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder().baseUrl(" https://damapi.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        return retrofit


    }
        val apiRetrofit: Api = getRetrofit().create(Api::class.java)

}
//H


