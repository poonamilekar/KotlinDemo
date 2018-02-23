package com.neosoft.kotlinapp.models

/**
 * Created by Poonam Ilekar on 27/12/17.
 */
class Employee constructor(name : String){

    val firstProperty="$name"//.also(::println)
    val secondProperty = "${name.length}"//.also(::println)
    init {
        println("Second initializer block that prints ${name.length}")
    }
    init {
        System.out.println("First property name : $name")
    }
}
