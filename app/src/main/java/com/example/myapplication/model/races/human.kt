package com.example.myapplication.model.races

import com.example.myapplication.model.interfaces.RawRace
import  com.example.myapplication.model.*

class Human : RawRace {
    override val moviment: Int = 9
    override val infravision: Int = 0
    override val alignment = Alignment.ANYTHING

    override fun getName(): String {
       return Human.getName();
    }

    override fun getDescription(): String {
        return Human.getDescription();
    }

    override val attributeMap: Map<String, String> = mapOf(
        "Learning" to "Description of Learning",
        "Adaptability" to "Description of Adaptability",
    )

    companion object
    {
        fun getName():String
        {
            return "Human";
        }
        fun getDescription():String
        {
            return "Versatile and adaptable beings with great learning capacity";
        }
    }
}