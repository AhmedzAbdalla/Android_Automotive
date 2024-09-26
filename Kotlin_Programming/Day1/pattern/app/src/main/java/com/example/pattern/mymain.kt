package com.example.pattern

fun main(){


    var str1 = "*"
    var str2 = "                    "
    var str3 = "*"
    var str: String

    for (i in 0 until 10) {
        //str = "$str1$str2$str3"

        str = str1+str2+str3

        println(str)

        str1 = str1.plus("*") // Equivalent to concat
        str2 = str2.substring(0, str2.length - 2) // Same as in Java
        str3 = str3.plus(" *") // Equivalent to concat
    }
}