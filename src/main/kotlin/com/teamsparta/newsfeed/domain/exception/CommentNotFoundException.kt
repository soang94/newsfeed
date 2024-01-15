package com.teamsparta.newsfeed.domain.exception

data class CommentNotFoundException(val commentId: Long?, val name: String) :
    RuntimeException("Comment not found with given commentId: $commentId, name: $name")
