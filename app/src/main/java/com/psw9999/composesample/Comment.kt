package com.psw9999.composesample

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