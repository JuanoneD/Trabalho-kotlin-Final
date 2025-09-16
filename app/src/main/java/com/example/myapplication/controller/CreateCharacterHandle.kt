package com.example.myapplication.controller
import Class.Mage
import Class.Warrior
import  com.example.myapplication.model.*
import com.example.myapplication.model.interfaces.RawClass
import com.example.myapplication.model.interfaces.RawRace
import com.example.myapplication.model.races.*
import com.example.myapplication.model.`class`.Cleric

class CreateCharacterHandle {
    private var option : StartOption = StartOption.CLASSIC;
    private var charRace : RawRace? = null;
    private var charClass : RawClass? = null;

    private var strength: Int = 0
    private var dexterity: Int = 0
    private var constitution: Int = 0
    private var intelligence: Int = 0
    private var wisdom: Int = 0
    private var charisma: Int = 0

    fun createCharacter():Character?
    {
      if(charClass == null || charRace == null)
      {
          return null;
      }
        val char = Character(charRace!!, charClass!!);
        char.strength = strength;
        char.dexterity = dexterity;
        char.constitution = constitution;
        char.intelligence = intelligence;
        char.wisdom = wisdom;
        char.charisma = charisma;

        return char;
    }

    fun setAttributes(attributeValues:Map<String, Pair<Int?, Int?>>)
    {
        attributeValues.forEach { (attributeName, valueAndIndex) ->
            val value = valueAndIndex.first ?: 0
            when (attributeName) {
                "Strength" -> strength = value
                "Dexterity" -> dexterity = value
                "Constitution" -> constitution = value
                "Intelligence" -> intelligence = value
                "Wisdom" -> wisdom = value
                "Charisma" -> charisma = value
            }
        }
    }

    fun setRace(race: Race)
    {
            when(race.name)
            {
                Human.getName() -> charRace = Human();
                Elf.getName() -> charRace = Elf();
                Dwarf.getName() -> charRace = Dwarf();
                Halfling.getName() -> charRace = Halfling();
                HalfElf.getName() -> charRace = HalfElf();
                Gnome.getName() -> charRace = Gnome();
            }
    }

    fun setRace(race : RawRace)
    {Halfling
        charRace = race;
    }

    fun getRace():RawRace?
    {
        return charRace;
    }

    fun setOptionStart(startedOption:StartOption)
    {
        option = startedOption
    }

    fun getOptionStart(): StartOption {
        return option;
    }
    
    // Attribute getters
    fun getStrength(): Int = strength
    fun getDexterity(): Int = dexterity
    fun getConstitution(): Int = constitution
    fun getIntelligence(): Int = intelligence
    fun getWisdom(): Int = wisdom
    fun getCharisma(): Int = charisma
    
    fun getAllAttributes(): Map<String, Int> {
        return mapOf(
            "Strength" to strength,
            "Dexterity" to dexterity,
            "Constitution" to constitution,
            "Intelligence" to intelligence,
            "Wisdom" to wisdom,
            "Charisma" to charisma
        )
    }
    
    fun setClass(characterClass: CharacterClass) {
        when (characterClass.name) {
            Warrior.getName() -> charClass = Warrior()
            Mage.getName() -> charClass = Mage()
            Cleric.getName() -> charClass = Cleric()
        }
    }
    
    fun setClass(rawClass: RawClass) {
        charClass = rawClass
    }
    
    fun getClass(): RawClass? {
        return charClass
    }
    
    companion object {
        @JvmStatic
        fun getRacesInfo(): List<Race>{
            return listOf(
                Race(
                    name = Human.getName(),
                    description = Human.getDescription(),
                    movement = 9,
                    infravision = 0,
                    alignment = "Any",
                    attributes = listOf("Learning", "Adaptability")
                ),
                Race(
                    name = Elf.getName(),
                    description = Elf.getDescription(),
                    movement = 9,
                    infravision = 18,
                    alignment = "Neutral",
                    attributes = listOf("Natural Perception", "Graceful", "Racial Training", "Immunities")
                ),
                Race(
                    name = Dwarf.getName(),
                    description = Dwarf.getDescription(),
                    movement = 6,
                    infravision = 18,
                    alignment = "Good",
                    attributes = listOf("Miners", "Vigor", "Big Weapons", "Enemy")
                ),
                Race(
                    name = Halfling.getName(),
                    description = Halfling.getDescription(),
                    movement = 6,
                    infravision = 0,
                    alignment = "Good",
                    attributes = listOf("Stealth", "Luck", "Small Size")
                ),
                Race(
                    name = HalfElf.getName(),
                    description = HalfElf.getDescription(),
                    movement = 9,
                    infravision = 18,
                    alignment = "Neutral",
                    attributes = listOf("Versatility", "Partial Training")
                ),
                Race(
                    name = Gnome.getName(),
                    description = Gnome.getDescription(),
                    movement = 6,
                    infravision = 18,
                    alignment = "Good",
                    attributes = listOf("Natural Magic", "Tinkering", "Forest Knowledge")
                )
            )
        }
        @JvmStatic
        fun getCharacterClasses(): List<CharacterClass> {
            return listOf(
                CharacterClass(
                    name = Warrior.getName(),
                    description = "A skilled fighter with mastery of weapons and armor. Warriors excel in melee combat and can use any weapon or armor.",
                    weapons = "Any weapons",
                    armors = "Any armor",
                    magic = "No magic items",
                    abilities = listOf("Parry", "Weapon Mastery", "Extra Attack")
                ),
                CharacterClass(
                    name = Mage.getName(),
                    description = "A wielder of arcane magic with access to powerful spells. Mages are physically frail but possess devastating magical abilities.",
                    weapons = "Any weapons",
                    armors = "Any armor",
                    magic = "No magic items",
                    abilities = listOf("Parry", "Weapon Mastery", "Extra Attack")
                ),
                CharacterClass(
                    name = Cleric.getName(),
                    description = "A divine spellcaster who serves a deity. Clerics combine healing magic with martial prowess, restricted to blunt weapons.",
                    weapons = "Small weapons only",
                    armors = "No armor",
                    magic = "Any magic items",
                    abilities = listOf("Arcane Magic", "Detect Magic", "Read Magic")
                )
            )
        }
    }
}