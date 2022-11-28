package com.loginapp.Recyclers

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.loginapp.Cat
import com.loginapp.DB.CatsDBHelper
import com.loginapp.Fragment.DetailFragment
import com.loginapp.R

// Definir classe RecyclerViewAdapter y su constructor
class RecyclerViewAdapter(var llistat: ArrayList<Cat>, var context: Context?, var db:CatsDBHelper)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return llistat.size
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.txtNom.text = llistat[position].getNom()
        holder.txtRaza.text = llistat[position].getRaza()
        holder.txtColor.text = llistat[position].getColor()

        holder.btnDelete.setOnClickListener (object:View.OnClickListener{
            override fun onClick(v: View?) {
                val item = llistat[position].id
                val confirmacion = AlertDialog.Builder(context!!)
                confirmacion.setMessage(
                    "¿Quieres eliminar a ${
                        llistat[holder.adapterPosition].getNom()
                    }?"
                )
                    .setPositiveButton("Si",
                        DialogInterface.OnClickListener { dialog, id ->
                            db.deleteCatById(item)
                            llistat.removeAt(position)
                            //
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, llistat.size)
                        })
                    .setNegativeButton("Cancelar",
                        DialogInterface.OnClickListener { dialog, id ->
                            Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show()
                        })
                // Create the AlertDialog object and return it
                confirmacion.create().show()
            }
        })

        holder.itemView.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                val activity = v!!.context as AppCompatActivity
                activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                    DetailFragment(llistat.get(holder.adapterPosition))).addToBackStack(null).commit()
            }
        })
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNom: TextView = view.findViewById(R.id.txtCatNom)
        val txtRaza: TextView = view.findViewById(R.id.textRaza)
        val txtColor: TextView = view.findViewById(R.id.textColor)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
    }
}