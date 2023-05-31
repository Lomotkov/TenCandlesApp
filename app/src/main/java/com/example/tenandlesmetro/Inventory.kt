package com.example.tenandlesmetro

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.tenandlesmetro.data.Inventory
import com.example.tenandlesmetro.data.Person
import com.example.tenandlesmetro.data.items.Item
import com.example.tenandlesmetro.databinding.FragmentInventoryBinding
import com.example.tenandlesmetro.utils.hideKeyboard
import com.example.tenandlesmetro.utils.saveToJson
import kotlinx.serialization.json.Json
import java.io.File

class Inventory : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentInventoryBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_inventory, container, false)
        val file = File(context?.filesDir, getString(R.string.jsonFileName))
        val person = Json.decodeFromString(Person.serializer(), file.readText())
        binding.inventoryText.setText(person.inventory.itemsToText())
        binding.saveInventory.setOnClickListener {
            it.findNavController().navigate(R.id.action_inventory_to_gameFragment)
            val inventoryText = binding.inventoryText.text.toString()
            person.inventory = Inventory(inventoryText.textToItems())
            hideKeyboard(it)
            person.saveToJson(context?.filesDir, getString(R.string.jsonFileName))
        }
        return binding.root
    }

    private fun String.textToItems(): Set<Item> = this.split("\n").map { Item(it) }.toSet()

}