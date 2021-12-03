package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.squareup.picasso.Picasso
import java.lang.Exception

class DetalleActivity : AppCompatActivity() {

    companion object {

        var itemGuardar: MenuItem? = null
        var itemEditar: MenuItem? = null
        var itemBorrar: MenuItem? = null

    }


    lateinit var ibLlamarDirectorD: ImageButton
    lateinit var ivCaratulaDetalle: ImageView
    lateinit var tvDirectorDetalles: TextView
    lateinit var tvTituloDetalle: TextView
    lateinit var tvAñoPeliculaDetalle: TextView
    lateinit var tvSinopsisDetalle: TextView
    lateinit var tvGeneroDetalle: TextView
    private lateinit var detallePelicula: Pelicula

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        ibLlamarDirectorD = findViewById(R.id.ibLlamarDirectorD)
        ivCaratulaDetalle = findViewById(R.id.ivCaratulaDetalle)
        tvDirectorDetalles = findViewById(R.id.tvDirectorDetalle)
        tvAñoPeliculaDetalle = findViewById(R.id.tvAñoPeliculaDetalle)
        tvTituloDetalle = findViewById(R.id.tvAñoPeliculaDetalle)
        tvSinopsisDetalle = findViewById(R.id.tvSinopsisDetalles)
        tvGeneroDetalle = findViewById(R.id.tvGeneroDetalle)
        ibLlamarDirectorD.setOnClickListener(){

            val telefono = "tel:604002983"
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(telefono)))
        }


    }
    override fun onResume() {
        super.onResume()

        if (intent.extras?.get("pelicula") != null) {
            detallePelicula = intent.extras?.get("pelicula") as Pelicula
            title = detallePelicula.titulo
            tvGeneroDetalle.setText(detallePelicula.genero)
            tvDirectorDetalles.setText(detallePelicula.director)
            tvTituloDetalle.setText(detallePelicula.titulo)
            tvSinopsisDetalle.setText(detallePelicula.sinopsis)
            Picasso.get().load(detallePelicula.imagenPanoramica).into(ivCaratulaDetalle)
        } else {
            title = "nueva pelicula"
            tvTituloDetalle.isEnabled = true
            tvGeneroDetalle.isEnabled = true
            tvSinopsisDetalle.isEnabled = true
            tvDirectorDetalles.isEnabled = true

        }


}
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle_pelicula, menu)
        if (menu != null) {
            itemGuardar = menu.findItem(R.id.action_guardar)
            itemBorrar = menu.findItem(R.id.action_borrar)
            itemEditar = menu.findItem(R.id.action_editar)


        }
        return true
    }
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (intent.extras?.get("pelicula") != null) {
            itemEditar?.isVisible = true
            itemGuardar?.isVisible = false
            itemBorrar?.isVisible = true

        } else {
            itemEditar?.isVisible = false
            itemGuardar?.isVisible = true
            itemBorrar?.isVisible = false

        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_editar -> {
                //cambio los campos a editables
                tvTituloDetalle.isEnabled = true
                tvGeneroDetalle.isEnabled = true
                tvSinopsisDetalle.isEnabled = true
                tvDirectorDetalles.isEnabled = true
                Toast.makeText(this, "Editar", Toast.LENGTH_SHORT).show()
                itemGuardar?.isVisible = true
                itemEditar?.isVisible = false
                itemBorrar?.isVisible = false


                return true
            }
            R.id.action_borrar -> {
                Toast.makeText(this, "Pelicula borrada", Toast.LENGTH_SHORT).show()
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                val dialog = builder.setTitle("Eliminar pelicula")
                    .setMessage("Estas a punto de eliminar la pelicula " + detallePelicula.titulo + ". ¿Estas seguro?.")
                    .setPositiveButton("Aceptar") { _, _ ->
                        //detallePelicula.remove(detallePelicula)
                        Toast.makeText(this, "Película Borrada", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .setNegativeButton("Cancelar", null).create()
                dialog.show()

                return true
            }
            R.id.action_guardar -> {
                if (tvSinopsisDetalle.text.toString().isEmpty() || tvDirectorDetalles.text.toString()
                        .isEmpty() || tvDirectorDetalles.text.toString().isEmpty()
                    || tvTituloDetalle.text.toString()
                        .isEmpty() || tvGeneroDetalle.text.toString().isEmpty()

                ) {


                    if (tvTituloDetalle.text.toString().isEmpty()) {
                        tvTituloDetalle.error = "El campo titulo no puede estar vacio"
                    }
                    if (tvSinopsisDetalle.text.toString().isEmpty()) {
                        tvSinopsisDetalle.error = "El campo nota no puede estar vacio"
                    }
                    if (tvDirectorDetalles.text.toString().isEmpty()) {
                        tvDirectorDetalles.error = "El campo Director no puede estar vacio"
                    }
                    if (tvGeneroDetalle.text.toString().isEmpty()) {
                        tvGeneroDetalle.error = "El campo Genero no puede estar vacio"
                    }

                } else {



                    if (intent.extras?.get("pelicula") == null) {

                    } else {
                    }
                    Toast.makeText(this, "Película Guardada", Toast.LENGTH_SHORT).show()
                    finish()


                }
                return true
            }
            R.id.action_llamar -> {
                try {
                }catch (exp: Exception){
                    Toast.makeText(
                        this,
                        "Whatsapp no está instalado",
                        Toast.LENGTH_LONG).show()
                }



                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

}