package com.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.loginapp.DB.CatsDBHelper
import com.loginapp.Fragment.FormulariFragment
import com.loginapp.Fragment.HomeFragment
import com.loginapp.Fragment.LlistatFragment

class BottomNavigation : AppCompatActivity() {
    // Pre-definir la base de datos
    companion object{
        lateinit var dbHelper: CatsDBHelper
    }
    // Función para que el NAV aparezca en todos los layout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_botton_navigation)

        // Definir e iniciar la base de datos
        dbHelper = CatsDBHelper(this)

        // Definir un layout 'Home' por defecto
        loadFragment(HomeFragment())

        // Crear y enlazar la variable bottomNavigation
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // Método para que el NAV esté esperando respuesta y así muestre un layout u otro
        bottomNavigation.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }

                R.id.nav_form -> {
                    loadFragment(FormulariFragment(dbHelper))
                    true
                }

                R.id.nav_list -> {
                    loadFragment(LlistatFragment(dbHelper))
                    true
                }
                else -> {false}
            }
        }
    }

    // Función para cargar un layout en concreto por pantalla
    fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    // Apagar base de datos
    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}