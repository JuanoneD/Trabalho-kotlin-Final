package com.example.myapplication.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.controller.CreateCharacterHandle
import com.example.myapplication.model.CharacterClass
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun SelectClassScreen(
    onClassSelected: (CharacterClass) -> Unit = {},
    onBackPressed: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header with back button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onBackPressed,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("← Back")
            }
            
            Text(
                text = "Select Your Class",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            
            // Spacer to balance the back button
            Spacer(modifier = Modifier.width(80.dp))
        }
        
        // Subtitle
        Text(
            text = "Choose your character's class to determine abilities and restrictions",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )
        
        // Class list
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(CreateCharacterHandle.getCharacterClasses()) { characterClass ->
                ClassCard(
                    characterClass = characterClass,
                    onClassSelected = { onClassSelected(characterClass) }
                )
            }
        }
    }
}

@Composable
fun ClassCard(
    characterClass: CharacterClass,
    onClassSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // Class name
            Text(
                text = characterClass.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            // Class description
            Text(
                text = characterClass.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp),
                lineHeight = 20.sp
            )
            
            // Class restrictions and abilities
            Column(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                // Weapons
                Row(
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Text(
                        text = "Weapons: ",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = characterClass.weapons,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                
                // Armor
                Row(
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Text(
                        text = "Armor: ",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = characterClass.armors,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                
                // Magic
                Row(
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(
                        text = "Magic: ",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = characterClass.magic,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            
            // Class abilities
            Text(
                text = "Class Abilities:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            
            Text(
                text = characterClass.abilities.joinToString(" • "),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            
            // Select button
            Button(
                onClick = onClassSelected,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = when (characterClass.name) {
                        "Warrior" -> MaterialTheme.colorScheme.primary
                        "Mage" -> MaterialTheme.colorScheme.secondary
                        "Cleric" -> MaterialTheme.colorScheme.tertiary
                        else -> MaterialTheme.colorScheme.primary
                    }
                )
            ) {
                Text(
                    text = "Choose ${characterClass.name}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectClassScreenPreview() {
    MyApplicationTheme {
        SelectClassScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ClassCardPreview() {
    MyApplicationTheme {
        ClassCard(
            characterClass = CreateCharacterHandle.getCharacterClasses().first(),
            onClassSelected = {}
        )
    }
}
