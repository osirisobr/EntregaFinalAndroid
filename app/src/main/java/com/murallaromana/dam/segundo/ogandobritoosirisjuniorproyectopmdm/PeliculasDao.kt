package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PeliculasDao {

    @Query("SELECT * from Pelicula ORDER BY idRoom ASC")
    fun findAll(): List<Pelicula>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(country: Pelicula)


}