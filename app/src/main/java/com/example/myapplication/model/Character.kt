package com.example.myapplication.model

import com.example.myapplication.model.interfaces.*
import com.example.myapplication.model.abstract.RawStatus


class Character(val race: RawRace, val characterClass: RawClass) : RawStatus() {
    
    var experience = 0;

    init {
        // Next Function in the future
    }
}