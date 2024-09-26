package com.example.kotlincalc

fun add(num1:Int,num2:Int,): Int {

    return num1+num2
}

fun mul(num1:Int,num2:Int,): Int {

    return num1*num2
}

fun sub(num1:Int,num2:Int,): Int {

    return num1-num2
}

fun divide(num1:Int,num2:Int,): Double {

    return if(num2 == 0){0.0}else{ (num1.toDouble() / num2.toDouble())}
    //return num1/num2
}

fun main()
{

    println("Enter the first number: ")
    val num1 = readln().toInt()
    println("Enter the second number: ")
    val num2 = readln().toInt()

    println("Choose an operation: 1. Add  2. Multiply  3. Subtract  4. Divide")
    val choice = readln().toInt()

    when (choice) {
        1 -> println("Result of addition: ${add(num1, num2)}")
        2 -> println("Result of multiplication: ${mul(num1, num2)}")
        3 -> println("Result of subtraction: ${sub(num1, num2)}")
        4 -> println("Result of division: ${divide(num1, num2)}")
        else -> println("Invalid operation choice.")
    }

}