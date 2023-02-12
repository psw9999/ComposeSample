package com.psw9999.composesample

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterDisplay(
    modifier: Modifier
) {
    var editedText by remember { mutableStateOf("") }
    var countText by remember { mutableStateOf("Start Copying") }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.testTag("Counter Display"),
            text = countText,
            style = TextStyle(
                fontSize = 36.sp,
                color = Color.White
            )
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .testTag("Input"),
            value = editedText,
            onValueChange = {
                editedText = it
            },
            label = {
                Text("Input")
            },
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                countText = processInput(editedText)
            }
        ) {
            Text(
                text = "Copy",
                style = TextStyle(
                    fontSize = 26.sp,
                    color = Color.White
                )
            )
        }
    }
}


private fun processInput(editedText: String): String {
    return try {
        val counterValue = editedText.toInt()
        "Counter = $counterValue"
    } catch (e: NumberFormatException) {
        "Invalid entry"
    }
}


