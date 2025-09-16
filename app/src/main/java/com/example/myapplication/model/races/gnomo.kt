package com.example.myapplication.model.races

import com.example.myapplication.model.interfaces.RawRace
import  com.example.myapplication.model.*

class Gnome : RawRace {
    override val moviment: Int = 6
    override val infravision: Int = 18
    override val alignment = Alignment.NEUTRAL

    override fun getName(): String {
        return Gnome.getName();
    }

    override fun getDescription(): String {
        return Gnome.getDescription();
    }

    override val attributeMap: Map<String, String> = mapOf(
        "Appraiser" to "Description of appraiser",
        "Tinker" to "Description of Tinker",
        "Sagacious and Vigorous" to "Description of Sagacious and Vigorous",
        "Restrictions" to "Description of Restrictions"
    )

    companion object
    {
        fun getName():String
        {
            return "Gnomo";
        }
        fun getDescription():String
        {
            return "Small forest dwellers with natural magic and tinkering skills";
        }
    }
}
