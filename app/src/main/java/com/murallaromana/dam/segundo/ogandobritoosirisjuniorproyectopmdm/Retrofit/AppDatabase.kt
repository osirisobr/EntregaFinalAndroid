package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Pelicula
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.PeliculasDao


@Database(entities = [Pelicula::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): PeliculasDao
    companion object {
        private var database: AppDatabase? = null
        // Singleton
        fun getDatabase(context: Context): AppDatabase? {
// ?: dice que si la parte izquierda es nula ejecute la de la derecha
            database ?: synchronized(this) {
                database = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "my-imdb"
                ).build()
            }
            return database
        }
    }
}