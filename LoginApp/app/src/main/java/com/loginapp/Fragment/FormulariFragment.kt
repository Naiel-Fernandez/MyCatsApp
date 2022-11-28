package com.loginapp.Fragment

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.loginapp.Cat
import com.loginapp.DB.CatsDBHelper
import com.loginapp.R

class FormulariFragment(dbH:CatsDBHelper) : Fragment() {
    // Define the data base on a variable
    var db: CatsDBHelper = dbH;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val v:View= inflater.inflate(R.layout.fragment_formulari, container, false)
        // Define las variables quue serán utilizadas por el layout(fragment_formulari.xml)
        val name:EditText = v.findViewById(R.id.txtCatName)
        val race:EditText = v.findViewById(R.id.txtCatRace)
        val color:EditText = v.findViewById(R.id.txtCatColor)
        val btm: Button = v.findViewById(R.id.btnInsert)
        val lblFormulariResult: TextView = v.findViewById(R.id.lblFormulariResult)
        val btmDeleteDatabase: Button = v.findViewById(R.id.btmDeleteDatabase)

        // Método para añadir un gato y comprobar que sus valores son rellenados
        // En caso de que sus valores sean correctos los guarda y los redefine en ""
        btm.setOnClickListener{
            if (name.getText().toString().isEmpty()){
                lblFormulariResult.text = "Nom no vàlid"
            } else if (race.getText().toString().isEmpty()){
                lblFormulariResult.text = "Race no vàlid"
            }else if (color.getText().toString().isEmpty()){
                lblFormulariResult.text = "Color no vàlid"
            }else{
                lblFormulariResult.text = "Informació válida"
                db.insertCat(Cat(0,name.getText().toString(),race.getText().toString(),color.getText().toString()))
                name.setText("")
                race.setText("")
                color.setText("")
            }
        }
        // Función para confirmar que se quiere eliminar todos los gatos de la base de datos
        // en caso afirmativo los elimina
        btmDeleteDatabase.setOnClickListener{
            val confirmacion = AlertDialog.Builder(requireContext())
            confirmacion.setMessage("¿Quieres eliminar todos los gatos guardados?")
                .setPositiveButton("Si",
                    DialogInterface.OnClickListener { dialog, id ->
                        db.deleteAllCats()
                        //BottomNavigation().loadFragment(LlistatFragment(db))
                    })
                .setNegativeButton("Cancelar",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show()
                    })
            confirmacion.create().show()
        }
        return v;
    }
}