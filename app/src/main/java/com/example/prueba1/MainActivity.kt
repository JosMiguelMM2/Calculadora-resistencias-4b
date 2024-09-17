package com.example.prueba1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prueba1.ui.theme.Prueba1Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Prueba1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ResistanceCalculator()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResistanceCalculator() {
    val colors = listOf("Negro", "Marrón", "Rojo", "Naranja", "Amarillo", "Verde", "Azul", "Violeta", "Gris", "Blanco")
    val colorValues = mapOf(
        "Negro" to 0, "Marrón" to 1, "Rojo" to 2, "Naranja" to 3, "Amarillo" to 4,
        "Verde" to 5, "Azul" to 6, "Violeta" to 7, "Gris" to 8, "Blanco" to 9
    )
    val colorHexValues = mapOf(
        "Negro" to "#000000",
        "Marrón" to "#8B4513",
        "Rojo" to "#FF0000",
        "Naranja" to "#FFA500",
        "Amarillo" to "#FFFF00",
        "Verde" to "#008000",
        "Azul" to "#0000FF",
        "Violeta" to "#EE82EE",
        "Gris" to "#808080",
        "Blanco" to "#FFFFFF"
    )

    val multiplierValues = mapOf(
        "Negro" to 1, "Marrón" to 10, "Rojo" to 100, "Naranja" to 1000, "Amarillo" to 10000,
        "Verde" to 100000, "Azul" to 1000000, "Violeta" to 10000000, "Gris" to 100000000, "Blanco" to 1000000000
    )

    val toleranceValues = mapOf(
        "Marrón" to 1,
        "Rojo" to 2,
        "Verde" to 0.5,
        "Azul" to 0.25,
        "Violeta" to 0.1,
        "Gris" to 0.05,
        "Dorado" to 5,
        "Plateado" to 10
    )

    var band1 by remember { mutableStateOf(colors[0]) }
    var band2 by remember { mutableStateOf(colors[0]) }
    var band3 by remember { mutableStateOf(colors[0]) }
    var band4 by remember { mutableStateOf(colors[0]) }
    var expanded1 by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    var expanded3 by remember { mutableStateOf(false) }
    var expanded4 by remember { mutableStateOf(false) }

    val resistanceValue: Long =
        ((colorValues[band1] ?: 0) * 10 + (colorValues[band2] ?: 0)) * (multiplierValues[band3] ?: 1).toLong()
    val toleranceValue = toleranceValues[band4] ?: 0
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Calculadora de resistencias", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(32.dp))

        //Banda 1
        Text(
            text = "Banda 1", style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded1,
            onExpandedChange = { expanded1 = !expanded1 }
        ) {
            TextField(
                value = "$band1 ${colorValues[band1]}",
                onValueChange = { band1 = it },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded1) },
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(android.graphics.Color.parseColor(colorHexValues[band1]))
                )
            )
            ExposedDropdownMenu(
                expanded = expanded1,
                onDismissRequest = { expanded1 = false }
            ) {
                colors.forEach { color ->
                    DropdownMenuItem(
                        text = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .background(Color(android.graphics.Color.parseColor(colorHexValues[color])))
                                    .clip(RoundedCornerShape(12.dp)),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = color)
                                Text(text = colorValues[color].toString())
                            }
                        },
                        onClick = {
                            band1 = color
                            expanded1 = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Banda 2", style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        ExposedDropdownMenuBox(
            expanded = expanded2,
            onExpandedChange = { expanded2 = !expanded2 }
        ) {
            TextField(
                value = "$band2 ${colorValues[band2]}",
                onValueChange = { band2 = it },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded2) },
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(android.graphics.Color.parseColor(colorHexValues[band2]))
                )
            )
            ExposedDropdownMenu(
                expanded = expanded2,
                onDismissRequest = { expanded2 = false }
            ) {
                colors.forEach { color ->
                    DropdownMenuItem(
                        text = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .background(Color(android.graphics.Color.parseColor(colorHexValues[color])))
                                    .clip(RoundedCornerShape(12.dp)),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = color)
                                Text(text = colorValues[color].toString())
                            }
                        },
                        onClick = {
                            band2 = color
                            expanded2 = false
                        }
                    )
                }
            }
        }

        Text(
            text = "Multiplicador", style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 12.dp, top = 16.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded3,
            onExpandedChange = { expanded3 = !expanded3 }
        ) {
            TextField(
                value = "$band3 ${multiplierValues[band3]}",
                onValueChange = { band3 = it },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded3) },
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(android.graphics.Color.parseColor(colorHexValues[band3]))
                )
            )
            ExposedDropdownMenu(
                expanded = expanded3,
                onDismissRequest = { expanded3 = false }
            ) {
                colors.forEach { color ->
                    DropdownMenuItem(
                        text = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .background(Color(android.graphics.Color.parseColor(colorHexValues[color])))
                                    .clip(RoundedCornerShape(12.dp)),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = color)
                                Text(text = multiplierValues[color].toString())
                            }
                        },
                        onClick = {
                            band3 = color
                            expanded3 = false
                        }
                    )
                }
            }
        }

        Text(
            text = "Tolerancia", style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 12.dp, top = 16.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded4,
            onExpandedChange = { expanded4 = !expanded4 }
        ) {
            TextField(
                value = "$band4 ${toleranceValues[band4]}",
                onValueChange = { band4 = it },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded4) },
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(android.graphics.Color.parseColor(colorHexValues[band4]))
                )
            )
            ExposedDropdownMenu(
                expanded = expanded4,
                onDismissRequest = { expanded4 = false }
            ) {
                colors.forEach { color ->
                    val valor = toleranceValues[color]

                    if (valor != null) {
                        DropdownMenuItem(
                            text = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(40.dp)
                                        .background(Color(android.graphics.Color.parseColor(colorHexValues[color])))
                                        .clip(RoundedCornerShape(12.dp)),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = color)
                                    Text(text = toleranceValues[color].toString())
                                }
                            },
                            onClick = {
                                band4 = color
                                expanded4 = false
                            }
                        )
                    }
                }
            }
        }

        Text(
            text = "Valor de la resistencia: $resistanceValue Ω ±$toleranceValue%",
            modifier = Modifier
                .padding(top = 25.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(8.dp)
                )
                .shadow(4.dp, RoundedCornerShape(8.dp))
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMultipleButtonsColumn() {
    //ResistanceCalculator()
}

