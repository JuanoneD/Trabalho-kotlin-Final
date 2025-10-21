package com.example.myapplication.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.CharacterEntity

@Composable
fun SavedCharactersScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val characterDao = AppDatabase.getDatabase(context).characterDao()
    val (characters, setCharacters) = remember { mutableStateOf<List<CharacterEntity>>(emptyList()) }

    LaunchedEffect(Unit) {
        setCharacters(characterDao.getAllCharacters())
    }

    Column(modifier = modifier.padding(16.dp)) {
        Button(onClick = onBackPressed) {
            Text("Back")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(characters) { character ->
                CharacterItem(character = character)
            }
        }
    }
}

@Composable
fun CharacterItem(character: CharacterEntity) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Race: ${character.race}")
            Text("Class: ${character.characterClass}")
            Text("Strength: ${character.strength}")
            Text("Dexterity: ${character.dexterity}")
            Text("Constitution: ${character.constitution}")
            Text("Intelligence: ${character.intelligence}")
            Text("Wisdom: ${character.wisdom}")
            Text("Charisma: ${character.charisma}")
        }
    }
}