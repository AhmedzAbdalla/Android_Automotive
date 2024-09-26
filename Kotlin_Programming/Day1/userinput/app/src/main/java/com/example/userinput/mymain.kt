package com.example.userinput

fun main(){


    val def : String= "default value"
    print("Enter your name: ")
    var input :String= readln();

    //if as expression
    input = if(0 == input.length){def}else{input} //
    println( input)
}