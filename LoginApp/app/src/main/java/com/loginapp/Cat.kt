package com.loginapp
// Creación de la clase 'Cat' y su constructor
class Cat (id:Int, nom:String, raza: String, color: String){
    var id = id
    var Nombre = nom
    var Raza = raza
    var Color = color

    // Getters
    @JvmName("getId1")
    fun getId():Int{
        return this.id
    }
    @JvmName("getNom1")
    fun getNom(): String {
        return this.Nombre
    }
    @JvmName("getRaza1")
    fun getRaza(): String{
        return this.Raza
    }
    @JvmName("getColor1")
    fun getColor(): String{
        return this.Color
    }

    // Setters
    @JvmName("setId1")
    fun setId(nouId:Int){
        this.id = nouId
    }
    @JvmName("setNom1")
    fun setNom(nouNom:String){
        this.Nombre = nouNom
    }
    @JvmName("setRaza1")
    fun setRaza(novaRaza:String){
        this.Raza = novaRaza
    }
    @JvmName("setColor1")
    fun setColor(nouColor:String){
        this.Color = nouColor
    }
}