package com.loginapp.DB
// Definir la classe de la base de datos
class CatsContacts {
    object CatsContract {
        val Cats = "cats"
        val Cats_Name = "nom"
        val Cats_Raze = "raza"
        val Cats_Color = "color"

        // Comando SQL para crear una tabla con los atributos definidos
        val SQL_CREATE_ENTRIES =
            "CREATE TABLE $Cats (" +
                    "id INTEGER PRIMARY KEY," +
                    "$Cats_Name TEXT," +
                    "$Cats_Raze TEXT," +
                    "$Cats_Color TEXT)"

        // Comando SQL para borrar la tabla
        val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $Cats"
    }
}