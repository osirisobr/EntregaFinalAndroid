package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm

import android.content.Context
import android.content.SharedPreferences

class SharePredferences(val context: Context) {
    private val SP = "SharedPreferences"
    private val preferences: SharedPreferences = context.getSharedPreferences(SP, 0)

    fun guardarDatos(usuario: String, contraseña: String) {

        preferences.edit().putString("USUARIO", usuario)
        preferences.edit().putString("CONTRASEÑA", contraseña)
    }

    fun recuperarDatos(datoARecuperar: String): String? {

        if (datoARecuperar == "USUARIO") {
            return preferences.getString("USUARIO", null)
        } else if (datoARecuperar == "CONTRASEÑA") {
            return preferences.getString("CONTRASEÑA", null)
        }
        return null
    }
}