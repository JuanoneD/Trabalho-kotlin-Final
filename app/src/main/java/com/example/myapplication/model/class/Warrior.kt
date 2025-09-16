package Class

import com.example.myapplication.model.interfaces.RawClass
import  com.example.myapplication.model.*
import com.example.myapplication.model.`class`.Cleric
import kotlin.arrayOf

class Warrior:RawClass{

    override val weapons: Weapons = Weapons.ANYTHING

    override val armors: Armors = Armors.ANYTHING

    override val magic: MagicItens = MagicItens.NONE

    override val tableoflevel: Map<Int, Array<String>> = 
    mapOf(
        1 to arrayOf("0","10","1","5","0"),
        2 to arrayOf("2000",ONEDTEN,"2","5","2500"),
        3 to arrayOf("4000",ONEDTEN,"3","6","5000")
    )

    override val classAbility: Map<String, String> = 
    mapOf(
        "Aparar" to "Description of Aparar",
        "Maestria em Arma" to "Description of Arma",
        "Ataque Extra" to "Description of Ataque Extra"
    )

    override fun getName(): String {
        return Warrior.getName();
    }

    companion object
    {
        fun getName():String
        {
            return "Warrior"
        }
    }
}