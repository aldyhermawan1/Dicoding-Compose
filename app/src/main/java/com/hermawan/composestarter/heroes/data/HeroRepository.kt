package com.hermawan.composestarter.heroes.data

import com.hermawan.composestarter.heroes.model.Hero
import com.hermawan.composestarter.heroes.model.HeroesData

class HeroRepository {

    fun getHeroes(): List<Hero> {
        return HeroesData.heroes
    }

    fun searchHeroes(query: String): List<Hero> {
        return HeroesData.heroes.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}