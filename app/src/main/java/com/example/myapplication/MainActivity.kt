package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.view.SelectAtributesScreen
import com.example.myapplication.view.SelectRaceScreen
import com.example.myapplication.view.SelectClassScreen
import com.example.myapplication.view.SetAttributesScreen
import com.example.myapplication.view.CharacterSummaryScreen
import com.example.myapplication.model.races.*
import com.example.myapplication.controller.CreateCharacterHandle
import com.example.myapplication.model.StartOption
import com.example.myapplication.view.*

val SELECT_START_SCREEN : String = "SelectStartScreen";
val SET_ATRIBUTES_SCREEN : String = "SetAtributesScreen";
val SELECT_CLASS_SCREEN : String = "SelectClassScreen";
val SELECT_RACE_SREEN : String = "SelectRaceScreen";
val CHARACTER_SUMMARY_SCREEN : String = "CharacterSummaryScreen";

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val createCharacterHandle: CreateCharacterHandle = CreateCharacterHandle();

        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CharacterCreationFlow(
                        modifier = Modifier.padding(innerPadding),
                        createCharacterHandle = createCharacterHandle
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterCreationFlow(modifier: Modifier = Modifier, createCharacterHandle: CreateCharacterHandle) {
    var currentScreen by remember { mutableStateOf(SELECT_START_SCREEN) }

    when (currentScreen) {
        SELECT_START_SCREEN -> {
            SelectAtributesScreen(
                onClassicSelected = {
                    createCharacterHandle.setOptionStart(StartOption.CLASSIC)
                    currentScreen = SET_ATRIBUTES_SCREEN
                },
                onHeroicSelected = {
                    createCharacterHandle.setOptionStart(StartOption.HEROIC)
                    currentScreen = SET_ATRIBUTES_SCREEN
                },
                onAdventurerSelected = {
                    createCharacterHandle.setOptionStart(StartOption.ADVENTURER)
                    currentScreen = SET_ATRIBUTES_SCREEN
                },
                modifier = modifier
            )
        }
        SET_ATRIBUTES_SCREEN ->{
            SetAttributesScreen(
                selectedMode = createCharacterHandle.getOptionStart(),
                onBackPressed = {
                    currentScreen = SELECT_START_SCREEN
                },
                onContinue = {attributeValues ->
                    createCharacterHandle.setAttributes(attributeValues);
                    currentScreen = SELECT_RACE_SREEN
                },
                modifier = modifier
            )
        }
        SELECT_RACE_SREEN -> {
            SelectRaceScreen(
                onRaceSelected = { race ->
                    createCharacterHandle.setRace(race);
                    currentScreen = SELECT_CLASS_SCREEN
                },
                onBackPressed = {
                    currentScreen = SET_ATRIBUTES_SCREEN
                },
                modifier = modifier
            )
        }
        SELECT_CLASS_SCREEN -> {
            SelectClassScreen(
                onClassSelected = { characterClass ->
                    createCharacterHandle.setClass(characterClass)
                    currentScreen = CHARACTER_SUMMARY_SCREEN
                },
                onBackPressed = {
                    currentScreen = SELECT_RACE_SREEN
                },
                modifier = modifier
            )
        }
        CHARACTER_SUMMARY_SCREEN -> {
            val character = createCharacterHandle.createCharacter()
            CharacterSummaryScreen(
                character = character,
                creationMode = createCharacterHandle.getOptionStart(),
                onBackPressed = {
                    currentScreen = SELECT_RACE_SREEN
                },
                onFinish = {
                    currentScreen = SELECT_START_SCREEN
                },
                modifier = modifier
            )
        }
    }
}