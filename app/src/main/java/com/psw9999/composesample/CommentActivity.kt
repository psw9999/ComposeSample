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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.sharp.SubdirectoryArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psw9999.composesample.ui.theme.ComposeSampleTheme

class CommentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { CommentTopAppBar() },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        CommentColumn(
            commentItems = addCommentsHeader(mockData),
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentTopAppBar() {
    TopAppBar(
        title = { Text(text = "Comment") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun CommentColumn(
    modifier: Modifier = Modifier,
    commentItems: List<CommentItem>
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = commentItems) { commentItem ->
            when(commentItem) {
                is CommentItem.CommentHeader -> {
                    CommentHeader(
                        commentItem
                    )
                }
                is CommentItem.CommentCard -> {
                    CommentCard(comment = commentItem.comment)
                }
            }
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
    comment: Comment,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    var isFollowing by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .padding(15.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = modifier
                .weight(1f)
        ) {
            KindLabel(
                comment.stockKinds
            )
            Text(
                text = comment.stockName,
                modifier = modifier.padding(
                    vertical = 4.dp
                ),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = comment.commentTitle,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp
            )
            if (expanded) {
                for (com in comment.commentList) {
                    Row(
                        modifier = modifier.padding(top = 3.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.SubdirectoryArrowRight,
                            contentDescription = null
                        )
                        Text(
                            modifier = modifier.padding(vertical = 5.dp, horizontal = 3.dp),
                            text = stringResource(
                                id = R.string.comment_list,
                                com.comment,
                                com.detailContent
                            ),
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { isFollowing = !isFollowing },
                modifier = modifier
                    .size(24.dp)
            ) {
                Icon(
                    modifier = modifier.fillMaxSize(1.0F),
                    imageVector = if (isFollowing) Icons.Filled.Star else Icons.Filled.StarBorder,
                    tint = if (isFollowing) Color.Yellow else Color.Black,
                    contentDescription = if (isFollowing) {
                        "ShowLess"
                    } else {
                        "ShowMore"
                    },
                )
            }
            Spacer(modifier = modifier.size(24.dp))
            Row {
                CountLabel(
                    count = comment.commentList.size,
                    modifier = modifier
                )
                IconButton(
                    onClick = { expanded = !expanded },
                    modifier = modifier
                        .size(24.dp)
                ) {
                    Icon(
                        modifier = modifier.fillMaxSize(1.0F),
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
    }
}

private fun addCommentsHeader(comments: List<Comment>): List<CommentItem> {
    val result = arrayListOf<CommentItem>()
    var headerDate = ""

    comments.forEach { comment ->
        if (headerDate != comment.registrationDate) {
            result.add(
                CommentItem.CommentHeader(
                    registrationDate = comment.registrationDate
                )
            )
            headerDate = comment.registrationDate
        }
        result.add(CommentItem.CommentCard(comment = comment))
    }
    return result
}

@Composable
fun CountLabel(
    count: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = Color.LightGray,
        shape = RoundedCornerShape(2.dp),
        content = {
            Text(
                modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp),
                text = count.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
        },
        contentColor = Color.Black
    )
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
                modifier = Modifier.padding(horizontal = 3.dp, vertical = 1.dp),
                text = stockKind,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 13.sp
            )
        },
        contentColor = Color.White
    )
}

@Composable
private fun CommentHeader(
    date: CommentItem.CommentHeader,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier.padding(
            horizontal = 10.dp,
            vertical = 10.dp
        ),
        color = MaterialTheme.colorScheme.background
    ) {
        Text(
            text = date.registrationDate
        )
    }
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
                commentItems = addCommentsHeader(mockData)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xF6F6F6)
@Composable
fun PreviewCommentScreen() {
    MaterialTheme {
        CommentScreen()
    }
}