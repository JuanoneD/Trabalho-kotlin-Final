package com.example.myapplication.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.model.StartOption
import com.example.myapplication.model.Dice
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun SetAttributesScreen(
    selectedMode: StartOption = StartOption.CLASSIC,
    onBackPressed: () -> Unit = {},
    onContinue: (attributeValues: Map<String, Pair<Int?, Int?>>) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var rolls by remember { mutableStateOf(listOf<Int>()) }
    var usedRollIndices by remember { mutableStateOf(setOf<Int>()) }
    var attributeValues by remember { 
        mutableStateOf(mapOf<String, Pair<Int?, Int?>>(
            "Strength" to (null to null),
            "Dexterity" to (null to null),
            "Constitution" to (null to null),
            "Intelligence" to (null to null),
            "Wisdom" to (null to null),
            "Charisma" to (null to null)
        ))
    }
    
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
                text = "Set Attributes",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            
            Spacer(modifier = Modifier.width(80.dp))
        }
        
        // Mode description
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = when (selectedMode) {
                        StartOption.CLASSIC -> "Classic Style"
                        StartOption.ADVENTURER -> "Adventurer Style" 
                        StartOption.HEROIC -> "Heroic Style"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = when (selectedMode) {
                        StartOption.CLASSIC -> "Roll 3d6 six times and distribute in fixed order"
                        StartOption.ADVENTURER -> "Roll 3d6 six times and distribute as desired"
                        StartOption.HEROIC -> "Roll 4d6 dropping lowest and distribute as desired"
                    },
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        
        // Roll dice button
        if (rolls.isEmpty()) {
            Button(
                onClick = {
                    rolls = when (selectedMode) {
                        StartOption.CLASSIC, StartOption.ADVENTURER -> {
                            (1..6).map { Dice.rool3d6().toInt() }
                        }
                        StartOption.HEROIC -> {
                            (1..6).map { Dice.rool4d6DropLowest().toInt() }
                        }
                    }
                    
                    // For classic mode, auto-assign in order
                    if (selectedMode == StartOption.CLASSIC) {
                        val attributes = listOf("Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma")
                        val newAttributeValues = attributeValues.toMutableMap()
                        val newUsedIndices = mutableSetOf<Int>()
                        rolls.forEachIndexed { index, roll ->
                            newAttributeValues[attributes[index]] = roll to index
                            newUsedIndices.add(index)
                        }
                        attributeValues = newAttributeValues.toMap()
                        usedRollIndices = newUsedIndices.toSet()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            ) {
                Text("Roll Dice", fontSize = 18.sp)
            }
        }
        
        // Show rolls
        if (rolls.isNotEmpty()) {
            Text(
                text = "Rolls: ${rolls.joinToString(", ")}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            // Attribute assignment
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                attributeValues.forEach { (attribute, valueAndIndex) ->
                    item {
                        AttributeCard(
                            attributeName = attribute,
                            currentValue = valueAndIndex.first,
                            availableRolls = if (selectedMode == StartOption.CLASSIC) {
                                emptyList()
                            } else {
                                rolls.mapIndexed { index, roll -> 
                                    roll to index 
                                }.filter { (_, index) -> 
                                    !usedRollIndices.contains(index) || attributeValues[attribute]?.second == index
                                }
                            },
                            onValueSelected = { selectedRoll, rollIndex ->
                                if (selectedMode != StartOption.CLASSIC) {
                                    val newAttributeValues = attributeValues.toMutableMap()
                                    val newUsedIndices = usedRollIndices.toMutableSet()
                                    
                                    // Clear previous assignment for this attribute
                                    attributeValues[attribute]?.second?.let { oldIndex ->
                                        newUsedIndices.remove(oldIndex)
                                    }
                                    
                                    // Set new assignment
                                    newAttributeValues[attribute] = selectedRoll to rollIndex
                                    newUsedIndices.add(rollIndex)
                                    
                                    attributeValues = newAttributeValues.toMap()
                                    usedRollIndices = newUsedIndices.toSet()
                                }
                            },
                            isClassicMode = selectedMode == StartOption.CLASSIC
                        )
                    }
                }
            }
            
            // Continue button
            if (attributeValues.values.all { it.first != null }) {
                Button(
                    onClick = { onContinue(attributeValues) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Continue", fontSize = 18.sp)
                }
            }
        }
    }
}

@Composable
fun AttributeCard(
    attributeName: String,
    currentValue: Int?,
    availableRolls: List<Pair<Int, Int>>,
    onValueSelected: (Int, Int) -> Unit,
    isClassicMode: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = attributeName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                
                if (currentValue != null) {
                    Text(
                        text = currentValue.toString(),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            
            if (!isClassicMode && currentValue == null && availableRolls.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    availableRolls.forEach { (roll, index) ->
                        OutlinedButton(
                            onClick = { onValueSelected(roll, index) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(roll.toString())
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SetAttributesScreenPreview() {
    MyApplicationTheme {
        SetAttributesScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun AttributeCardPreview() {
    MyApplicationTheme {
        AttributeCard(
            attributeName = "Strength",
            currentValue = 14,
            availableRolls = listOf(12 to 0, 15 to 1, 8 to 2),
            onValueSelected = { _, _ -> },
            isClassicMode = false
        )
    }
}
