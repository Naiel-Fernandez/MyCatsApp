package com.loginapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loginapp.Cat
import com.loginapp.DB.CatsDBHelper
import com.loginapp.R
import com.loginapp.Recyclers.RecyclerViewAdapter

class LlistatFragment(dbHelper: CatsDBHelper) : Fragment() {
    val db: CatsDBHelper =dbHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_llistat, container, false)

        // Definición de variables que se mostrarán en el layout(fragment_llistat.xml)
        val llistat: ArrayList<Cat> = db.getAllCats()
        val recyclerView:RecyclerView = view.findViewById(R.id.recyclerLlistat)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val madapter = RecyclerViewAdapter(llistat, context, db)
        recyclerView.adapter = madapter

        // No me acuerdo de lo que hace, he de investigar
        //recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        return view
    }
}
