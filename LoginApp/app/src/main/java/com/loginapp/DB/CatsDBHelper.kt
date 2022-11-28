package com.loginapp.DB

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.loginapp.Cat
import com.loginapp.DB.CatsContacts.CatsContract.Cats
import com.loginapp.DB.CatsContacts.CatsContract.Cats_Color
import com.loginapp.DB.CatsContacts.CatsContract.Cats_Name
import com.loginapp.DB.CatsContacts.CatsContract.Cats_Raze
import com.loginapp.DB.CatsContacts.CatsContract.SQL_CREATE_ENTRIES
import com.loginapp.DB.CatsContacts.CatsContract.SQL_DELETE_ENTRIES
import kotlin.collections.ArrayList

class CatsDBHelper (var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // Pre-definir la versión de la base de datos y su nombre.
    companion object {
        const val DATABASE_VERSION = 5
        const val DATABASE_NAME = "Cats.db"
    }
    // Función para crear la tabla de Cats a la base de datos
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }
    // Función para borrar loos datos de la base de datos y reiniciarla.
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    // Función para añadir un gato
    fun insertCat(l: Cat) {
        val values = ContentValues()
        values.put(Cats_Name, l.Nombre)
        values.put(Cats_Raze, l.Raza)
        values.put(Cats_Color, l.Color)

         val db = this.writableDatabase
        db.insert(Cats, null, values)
        Toast.makeText(context, "Cat afegit", Toast.LENGTH_SHORT).show()

    }
    // Función para definir la cadena de gatos con su información
    @SuppressLint("Range")
    fun getAllCats(): ArrayList<Cat> {
        val catList:ArrayList<Cat> = ArrayList()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $Cats", null)

        if(cursor.moveToFirst()){
            do{
                val id:Int = cursor.getInt(cursor.getColumnIndex("id"))
                val nom:String = cursor.getString(cursor.getColumnIndex(Cats_Name))
                val raza:String = cursor.getString(cursor.getColumnIndex(Cats_Raze))
                val color:String = cursor.getString(cursor.getColumnIndex(Cats_Color))
                val cat = Cat(id, nom, raza, color)
                catList.add(cat)
            }while(cursor.moveToNext())
        }
        return catList
    }
    // Función para borrar un gato, según su id, de la base de datos
    fun deleteCatById(id:Int){
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $Cats WHERE id = $id")
        Toast.makeText(context, "Cat eliminat correctament", Toast.LENGTH_SHORT).show()
    }
    // Función para borrar todos los gatos de la base de datos
    fun deleteAllCats(){
        val db = this.writableDatabase
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
        Toast.makeText(context, "Cats eliminats correctament", Toast.LENGTH_SHORT).show()
    }
}