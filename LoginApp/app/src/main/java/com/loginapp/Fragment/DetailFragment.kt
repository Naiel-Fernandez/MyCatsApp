package com.loginapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.loginapp.Cat
import com.loginapp.R

class DetailFragment(cat:Cat) : Fragment() {
    val cat:Cat = cat
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val v:View= inflater.inflate(R.layout.fragment_detail, container, false)
        // Define las variables quue serán utilizadas por el layout(fragment_detail.xml)
        val name: TextView = v.findViewById(R.id.txtDetailName)
        val race: TextView = v.findViewById(R.id.txtDetailRace)
        val color: TextView = v.findViewById(R.id.txtDetailColor)
        // Definir valores segun el view seleccionado
        name.setText(cat.getNom())
        race.setText(cat.getRaza())
        color.setText(cat.getColor())

        return v
    }
}