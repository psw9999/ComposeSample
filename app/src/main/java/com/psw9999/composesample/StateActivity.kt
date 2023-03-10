package com.psw9999.composesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.psw9999.composesample.ui.theme.ComposeSampleTheme

class StateActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WellnessScreen()
                }
            }
        }
    }

}

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }

    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            var showTask by remember { mutableStateOf(true) }
            if (showTask) {
                WellnessTaskItem(
                    onClose = { showTask = false },
                    taskName = "Have you taken your 15 minute walk today?"
                )
            }
            Text(
                text = "You've had $count glasses."
            )
        }

        Row(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Button(
                onClick = { count++ },
                enabled = count < 10
            ) {
                Text("Add One")
            }
            Button(
                onClick = { count = 0 },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Clear Water count")
            }
        }
    }
}

//@Composable
//fun WellnessTaskItem(
//    taskName: String,
//    onClose: () -> Unit = { },
//    modifier: Modifier = Modifier
//) {
//    Row(
//        modifier = modifier,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            modifier = Modifier
//                .weight(1f)
//                .padding(start = 16.dp),
//            text = taskName
//        )
//        IconButton(
//            onClick = onClose
//        ) {
//            Icon(
//                Icons.Filled.Close, contentDescription = "Close"
//            )
//        }
//    }
//}

@Composable
fun StatelessCounter(
    count: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(
            onClick = onIncrement,
            modifier = Modifier.padding(top = 8.dp),
            enabled = count < 10
        ) {
            Text("Add one")
        }
    }
}

@Composable
fun StatefulCounter(
    modifier: Modifier = Modifier
) {
    var waterCount by rememberSaveable { mutableStateOf(0) }
    var juiceCount by rememberSaveable { mutableStateOf(0) }
    Column {
        StatelessCounter(waterCount, { waterCount++ })
        StatelessCounter(count = juiceCount, onIncrement = { juiceCount++ })
    }

}

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    stateViewModel: StateViewModel = viewModel()
) {
    val responseState by stateViewModel.responseState.collectAsState()
    val list = remember { getWellnessTasks().toMutableStateList() }
    when(responseState) {
        is ResponseState.Init, ResponseState.Loading, ResponseState.Error -> LoadingScreen(modifier)
        is ResponseState.Success -> WellnessTasksList(
            list = list,
            onCloseTask = { task -> list.remove(task) }
        )
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

@Composable
fun WellnessTaskItem(taskName: String, modifier: Modifier = Modifier, onClose: () -> Unit) {
    var checkedState by rememberSaveable { mutableStateOf(false) }

    WellnessTaskItem(
        taskName = taskName,
        checked = checkedState,
        onCheckedChange = { checkedState = !checkedState },
        onClose = onClose,
        modifier = modifier
    )

}

data class WellnessTask(val id: Int, val label: String)

private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }

@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask> = remember { getWellnessTasks() },
    onCloseTask: (WellnessTask) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = modifier
    ) {
        items(
            items = list,
            key = { task -> task.id }
        ) { task ->
            WellnessTaskItem(taskName = task.label) {
                onCloseTask(task)
            }
        }
    }
}

@Preview
@Composable
fun PreviewWaterCounter() {
    ComposeSampleTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            StatefulCounter()

            val list = remember { getWellnessTasks().toMutableStateList() }
            WellnessTasksList(
                list = list,
                onCloseTask = { task -> list.remove(task) }
            )
        }
    }
}

