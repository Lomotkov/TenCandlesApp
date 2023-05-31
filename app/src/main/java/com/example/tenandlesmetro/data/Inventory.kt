package com.example.tenandlesmetro.data

import com.example.tenandlesmetro.data.items.Item
import kotlinx.serialization.Serializable

@Serializable
data class Inventory(
    val items: Set<Item>
) {
    fun itemsToText() = items.joinToString("\n") { it.name }
}
