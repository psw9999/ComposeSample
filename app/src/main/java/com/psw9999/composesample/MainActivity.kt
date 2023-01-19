package com.psw9999.composesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.psw9999.composesample.ui.theme.ComposeSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                InitScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun InitScreen(
    modifier: Modifier = Modifier
) {
    val shouldShowOnBoard = rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnBoard.value) {
            OnboardScreen(onContinueClicked = { shouldShowOnBoard.value = false })
        } else {
            CardList()
        }
    }
}

@Composable
fun OnboardScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun CardList(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            TestCard(name = name)
        }
    }
}

@Composable
private fun TestCard(name: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name = name)
    }
}

@Composable
fun CardContent(name: String) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                //.padding(bottom = extraPadding.coerceAtLeast(0.dp))
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold
            )
            if (expanded) {
                Text(
                    text = ("Compose ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4)
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    "ShowLess"
                } else {
                    "ShowMore"
                }
            )
        }
    }

}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    ComposeSampleTheme {
        InitScreen()
    }
}