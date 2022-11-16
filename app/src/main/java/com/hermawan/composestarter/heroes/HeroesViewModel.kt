package com.hermawan.composestarter.heroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hermawan.composestarter.heroes.data.HeroRepository
import com.hermawan.composestarter.heroes.model.Hero
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HeroesViewModel(repository: HeroRepository) : ViewModel() {

    private val _groupedHeroes = MutableStateFlow(
        repository.getHeroes()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupedHeroes: StateFlow<Map<Char, List<Hero>>> get() = _groupedHeroes
}

class ViewModelFactory(private val repository: HeroRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeroesViewModel::class.java)) {
            return HeroesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}