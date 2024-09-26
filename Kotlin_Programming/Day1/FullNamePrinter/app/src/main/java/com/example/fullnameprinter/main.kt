package com.example.fullnameprinter

fun main()
{

    val def : String= "one or both names are empty"
    print("Enter your first name: ")
    var fname:String = readln()
    print("Enter your last name: ")
    var lname:String = readln()


    var input = if(0 == fname.length || 0 == lname.length){def}else{"$fname $lname"}

//    templates
    println("$input")

}