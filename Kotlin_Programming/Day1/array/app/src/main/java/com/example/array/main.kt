package com.example.arrayfiller
import java.util.Random


fun main(){

    val len : Int = 100

    val arr = arrayOfNulls<Int>(len)

    val rand = Random()

    for(x in 0 .. 99)
    {
        arr.set(x, rand.nextInt(100))

    }

    for(x in 0 .. 99)
    {
        val temp= arr.get(x)
        if (temp != null) {
            when
            {
                temp > 0 && temp <=10 -> println(temp)
            }
        }
    }

}