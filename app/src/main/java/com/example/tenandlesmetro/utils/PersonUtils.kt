package com.example.tenandlesmetro.utils

import com.example.tenandlesmetro.data.Person
import kotlinx.serialization.json.Json
import java.io.File

fun Person.saveToJson(path: File?, fileName: String) {
    val jsonString =  Json.encodeToString(Person.serializer(),this)
    File(path, fileName).writeText(jsonString)
}