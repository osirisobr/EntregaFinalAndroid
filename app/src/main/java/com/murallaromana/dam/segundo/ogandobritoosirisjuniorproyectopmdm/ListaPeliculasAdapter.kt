package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ListaPeliculasAdapter(val peliculas : List<Pelicula>?, val context: Context) : RecyclerView.Adapter<ListaPeliculasAdapter.PeliculaHolder>() {


    class PeliculaHolder  (view: View) : RecyclerView.ViewHolder(view){
        val tvTitulo = view.findViewById<TextView>(R.id.tvTituloAño)
        val tvDuracion = itemView.findViewById<TextView>(R.id.tvDuracion)
        val tvDirector = itemView.findViewById<TextView>(R.id.tvRating)
        val ivCaratula = itemView.findViewById<ImageView>(R.id.ivCaratula)
        val tvRating = view.findViewById<TextView>(R.id.tvRating)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaHolder {
        val inflater = LayoutInflater.from(context).inflate(R.layout.item_pelicula, parent, false)

        return PeliculaHolder(inflater)
    }

    override fun onBindViewHolder(holder: PeliculaHolder, position: Int) {
        val Pelicula = peliculas?.get(position)

        holder.tvTitulo.setText(Pelicula?.titulo)
        holder.tvDuracion.setText(Pelicula?.duracion)
        holder.tvDirector.setText(Pelicula?.director)
        holder.tvRating.setText(Pelicula?.rating)
        Picasso.get().load(Pelicula?.url).into(holder.ivCaratula)
        holder.itemView.setOnClickListener {
        val intent = Intent(holder.itemView.context, DetalleActivity::class.java)
        intent.putExtra("pelicula",Pelicula)
        holder.itemView.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int{
        return peliculas!!.size
    }


}
