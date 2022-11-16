package com.hermawan.composestarter.heroes.data

import com.hermawan.composestarter.heroes.model.Hero
import com.hermawan.composestarter.heroes.model.HeroesData

class HeroRepository {
    fun getHeroes(): List<Hero> {
        return HeroesData.heroes
    }
}