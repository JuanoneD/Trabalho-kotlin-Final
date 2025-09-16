package com.example.myapplication.model.races

import com.example.myapplication.model.interfaces.RawRace
import  com.example.myapplication.model.*

class HalfElf : RawRace {
    override val moviment: Int = 9
    override val infravision: Int = 9
    override val alignment = Alignment.NEUTRAL

    override fun getName(): String {
        return HalfElf.getName();
    }

    override fun getDescription(): String {
        return HalfElf.getDescription();
    }

    override val attributeMap: Map<String, String> = mapOf(
        "Learning" to "Description of Learning",
        "Graceful and vigorous" to "Description of Graceful and vigorous",
        "Extra language" to "Description of extra language",
        "Immunities" to "Description of Immunities"
    )

    companion object
    {
        fun getName():String
        {
            return "Half Elf";
        }
        fun getDescription():String
        {
            return "Balanced between human adaptability and elven grace";
        }
    }
}
