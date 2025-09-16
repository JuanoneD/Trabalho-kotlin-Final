package com.example.myapplication.model.races

import com.example.myapplication.model.interfaces.RawRace
import  com.example.myapplication.model.*

class Elf : RawRace {
    override val moviment: Int = 9
    override val infravision: Int = 18
    override val alignment = Alignment.NEUTRAL

    override val attributeMap: Map<String, String> = mapOf(
        "Natural Perception" to "Description of Natural Perception",
        "Graceful" to "Description of Graceful",
        "Racial training" to "Description of Racial training",
        "Immunities" to "Description of Immunities"
    )

    override fun getName(): String {
        return Elf.getName();
    }

    override fun getDescription(): String {
        return Elf.getDescription();
    }

    companion object
    {
        fun getName():String
        {
            return "Elf";
        }
        fun getDescription():String
        {
            return "Graceful beings with natural perception and magical training";
        }
    }
}