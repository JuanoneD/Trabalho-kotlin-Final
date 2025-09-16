package com.example.myapplication.model.`class`

import com.example.myapplication.model.interfaces.RawClass
import  com.example.myapplication.model.*
import kotlin.arrayOf

class Cleric:RawClass{

    override val weapons: Weapons = Weapons.LITTLE

    override val armors: Armors = Armors.NONE

    override val magic: MagicItens = MagicItens.ANYTHING

    override val tableoflevel: Map<Int, Array<String>> = 
    mapOf(
        1 to arrayOf("0","10","1","5","0"),
        2 to arrayOf("1500",ONEDEIGTH,"1","5","2000"),
        3 to arrayOf("3000",ONEDEIGTH,"1","5","4000")
    )

    override val classAbility: Map<String, String> = 
    mapOf(
        "Magia Arcanas" to "Description of Magia Arcanas",
        "Detectar Magias" to "Description of Detectar Magias",
        "Ler Magias" to "Description of Ler Magias"
    )

    override fun getName(): String {
        return Cleric.getName();
    }

    companion object
    {
        fun getName():String
        {
            return "Cleric"
        }
    }
}