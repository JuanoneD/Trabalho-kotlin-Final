package com.example.myapplication.model.interfaces

import com.example.myapplication.model.*

interface RawClass {
    
    val weapons: Weapons
    val armors: Armors
    val magic: MagicItens

    fun getName():String;

    val tableoflevel: Map<Int, Array<String>>

    val classAbility: Map<String, String>
}