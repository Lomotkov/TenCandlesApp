package com.example.tenandlesmetro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.tenandlesmetro.data.Person
import com.example.tenandlesmetro.data.Sex
import com.example.tenandlesmetro.databinding.FragmentGameBinding
import kotlinx.serialization.json.Json
import java.io.File

class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentGameBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        binding.bindPersonToTextViews()
        binding.inventoryMini.setOnClickListener {
            it.findNavController().navigate(R.id.action_gameFragment_to_inventory)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun FragmentGameBinding.bindPersonToTextViews() {
        val file = File(context?.filesDir,  getString(R.string.jsonFileName))
        val person = Json.decodeFromString(Person.serializer(), file.readText())
        nameText.text = person.name
        badText.text = person.badPersonTrait
        goodText.text = person.goodPersonTrait
        secretText.text = person.secret
        inventoryMini.text = person.inventory.itemsToText()
        ageTextGame.text = person.age
        sexImg.setImageResource(
            when(person.sex) {
                Sex.Male -> R.drawable.male
                Sex.Female -> R.drawable.female
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.game_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.inventory -> findNavController().navigate(R.id.action_gameFragment_to_inventory)
            R.id.historyFragment -> findNavController().navigate(R.id.action_gameFragment_to_historyFragment)
            R.id.person_history -> findNavController().navigate(R.id.action_gameFragment_to_person_history)
        }
        return super.onOptionsItemSelected(item)
    }

}