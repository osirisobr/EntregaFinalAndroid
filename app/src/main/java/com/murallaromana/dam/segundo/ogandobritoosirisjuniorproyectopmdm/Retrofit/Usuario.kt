package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit

import com.google.gson.annotations.SerializedName

class Usuario(
   @SerializedName("email") var correo: String,
   @SerializedName("password") var contraseña: String

)


