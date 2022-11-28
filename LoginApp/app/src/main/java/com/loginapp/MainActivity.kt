package com.loginapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creation of variables connected to layout 'activity_main.xml'
        val btnSignIn: Button = findViewById(R.id.btnSignIn)
        val txtUsername:EditText = findViewById(R.id.txtUsername)
        val txtPassword: EditText = findViewById(R.id.txtPassword)
        val lblLoginResult: TextView = findViewById(R.id.lblLoginResult)

        // Hacemos que el botón de login verifique que los datos introducidos de usuario y
        // contraseña sean correctas, si lo son te envia al 'fragment_home.xml'
        btnSignIn.setOnClickListener{
            // Restricción para verificar que el nombre y contraseña son correctos
            if (txtUsername.text.toString() == "admin" && txtPassword.text.toString() == "admin"){
                val intent = Intent(this, BottomNavigation::class.java)
                startActivity(intent)
                lblLoginResult.text = "Correct login"
            }else{
                lblLoginResult.text = "Incorrect login"
            }
        }
    }
}