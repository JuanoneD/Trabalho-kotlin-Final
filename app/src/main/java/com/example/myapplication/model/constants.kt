package com.example.myapplication.model

const val STARTVALUE = 0

//dices
val ONEDTEN = "1d10"
val ONEDEIGTH = "1d8"
val ONEDFOUR = "1d4"

enum class StartOption {
    CLASSIC,
    ADVENTURER,
    HEROIC
}

enum class Alignment {
    GOOD,
    NEUTRAL,
    EVIL,
    ANYTHING
}

enum class Weapons {
    ANYTHING,
    LITTLE
}

enum class Armors {
    ANYTHING,
    NONE
}

enum class MagicItens {
    ANYTHING,
    NONE
}

data class AttributeMode(
    val option: StartOption,
    val name: String,
    val title: String,
    val description: String,
    val mechanics: String,
    val probability: String
)

data class Race(
    val name: String,
    val description: String,
    val movement: Int,
    val infravision: Int,
    val alignment: String,
    val attributes: List<String>
)

data class CharacterClass(
    val name: String,
    val description: String,
    val weapons: String,
    val armors: String,
    val magic: String,
    val abilities: List<String>
)