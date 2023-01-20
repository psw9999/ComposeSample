package com.psw9999.composesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.psw9999.composesample.ui.theme.ComposeSampleTheme

class CommentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
            }
        }
    }
}

@Composable
fun KindLabel(
    stockKind: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = Color.Red,
        content = {
            Text(
                modifier = modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                text = stockKind
            )
        },
        contentColor = Color.White
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewKindLabel() {
    ComposeSampleTheme {
        KindLabel(
            "공모주"
        )
    }
}