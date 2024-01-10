package com.teamsparta.newsfeed.domain.comment.service

import com.teamsparta.newsfeed.domain.comment.dto.CommentResponse
import com.teamsparta.newsfeed.domain.comment.dto.CreateCommentRequest
import com.teamsparta.newsfeed.domain.comment.dto.DeleteCommentRequest
import com.teamsparta.newsfeed.domain.comment.dto.UpdateCommentRequest

interface CommentService {
    fun createComment(request: CreateCommentRequest): CommentResponse
    fun updateComment(request: UpdateCommentRequest): CommentResponse
    fun deleteComment(request: DeleteCommentRequest): Unit
}