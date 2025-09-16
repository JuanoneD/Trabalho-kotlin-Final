package com.example.myapplication.model.races

import com.example.myapplication.model.interfaces.RawRace
import  com.example.myapplication.model.*

class Halfling : RawRace {
    override val moviment: Int = 6
    override val infravision: Int = 0
    override val alignment = Alignment.NEUTRAL

    override fun getName(): String {
        return Halfling.getName();
    }

    override fun getDescription(): String {
        return Halfling.getDescription();
    }

    override val attributeMap: Map<String, String> = mapOf(
        "Stealthy" to "Description of Stealthy",
        "Unfearing" to "Description of Unfearing",
        "Sharpshooter" to "Description of Sharpshooter",
        "Little" to "Description of Little",
        "Restrictions" to "Description of Restrictions"
    )

    companion object
    {
        fun getName():String
        {
            return "Halfling";
        }
        fun getDescription():String
        {
            return "Small but brave folk with natural stealth and good fortune";
        }
    }
}
