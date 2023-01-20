package com.psw9999.composesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
private fun CommentColumn(
    modifier: Modifier = Modifier,
    comments: List<Comment>
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = comments) { comment ->
            CommentCard(comment)
        }
    }
}

@Composable
private fun CommentCard(
    comment: Comment,
) {
    Card(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        CommentContent(comment)
    }
}

@Composable
private fun CommentContent(
    comment: Comment
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(10.dp)
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
                .padding(vertical = 8.dp)
        ) {
            KindLabel(
                comment.stockKinds
            )
            Text(
                text = comment.stockName,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = comment.commentTitle,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun KindLabel(
    stockKind: String
) {
    Surface(
        modifier = Modifier,
        color = Color.Red,
        content = {
            Text(
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                text = stockKind,
                fontWeight = FontWeight.Bold
            )
        },
        contentColor = Color.White
    )
}

@Preview(showBackground = true, backgroundColor = 0x8C8C8C00)
@Composable
fun PreviewKindLabel() {
    ComposeSampleTheme {
        KindLabel(
            "공모주"
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xF6F6F6)
@Composable
fun PreviewCommentColumns() {
    ComposeSampleTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            CommentColumn(
                modifier = Modifier.fillMaxSize(),
                comments = mockData)
        }
    }
}