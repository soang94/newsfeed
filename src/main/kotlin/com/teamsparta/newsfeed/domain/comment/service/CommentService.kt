package com.teamsparta.newsfeed.domain.comment.service

import com.teamsparta.newsfeed.domain.comment.dto.CommentResponse
import com.teamsparta.newsfeed.domain.comment.dto.CreateCommentRequest
import com.teamsparta.newsfeed.domain.comment.dto.DeleteCommentRequest
import com.teamsparta.newsfeed.domain.comment.dto.UpdateCommentRequest

interface CommentService {
    fun createComment(request: CreateCommentRequest, articleId: Long): CommentResponse
    fun updateComment(articleId: Long, commentId: Long, request: UpdateCommentRequest): CommentResponse
    fun deleteComment(request: DeleteCommentRequest)
}