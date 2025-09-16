package com.example.myapplication.model.interfaces
import com.example.myapplication.model.*

interface RawRace {
    val moviment: Int
    val infravision: Int
    val alignment: Alignment

    fun getName():String;
    fun getDescription():String;

    val attributeMap: Map<String, String>
}