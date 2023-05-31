package com.example.tenandlesmetro.data

import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val name: String,
    val age: String,
    val sex: Sex,
    val description: String,
    val secret: String,
    val goodPersonTrait: String,
    val badPersonTrait: String,
    var inventory: Inventory,
)
