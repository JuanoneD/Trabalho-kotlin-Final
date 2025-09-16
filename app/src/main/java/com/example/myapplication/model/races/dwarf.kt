package com.example.myapplication.model.races

import com.example.myapplication.model.interfaces.RawRace
import  com.example.myapplication.model.*

class Dwarf : RawRace {
    override val moviment: Int = 6
    override val infravision: Int = 18
    override val alignment = Alignment.GOOD

    override val attributeMap: Map<String, String> = mapOf(
        "Miners" to "Description of Miners",
        "Vigor" to "Description of Vigor",
        "Big weapons" to "Description of Big weapons",
        "Enemy" to "Description of Enemy"
    )

    override fun getName(): String {
        return Dwarf.getName();
    }

    override fun getDescription(): String {
        return Dwarf.getDescription();
    }

    companion object
    {
        fun getName():String
        {
            return "Dwarf"
        }
        fun getDescription():String
        {
            return "Sturdy mountain folk with mining expertise and weapon mastery";
        }
    }
}
