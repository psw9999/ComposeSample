package com.psw9999.composesample

sealed class CommentItem {
    data class CommentHeader(
        val registrationDate: String
    ) : CommentItem()

    data class CommentCard(
        val comment: Comment
    ) : CommentItem()
}

data class Comment(
    val commentIndex: Long,
    val ipoIndex: Long,
    val writer: String,
    val stockName: String,
    val stockKinds: String,
    val commentTitle: String,
    val commentList: List<CommentList>,
    val registrationDate: String,
    val isFollowing: Boolean = false
)

data class CommentList(
    val type: String,
    val comment: String,
    val detailContent: String
)