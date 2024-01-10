package com.teamsparta.newsfeed.domain.comment.controller

import com.teamsparta.newsfeed.domain.comment.dto.CommentResponse
import com.teamsparta.newsfeed.domain.comment.dto.CreateCommentRequest
import com.teamsparta.newsfeed.domain.comment.dto.DeleteCommentRequest
import com.teamsparta.newsfeed.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.newsfeed.domain.comment.service.CommentService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/article/{articleId}/comments")
@RestController
class CommentController(
    val commentService: CommentService
) {
    @Operation(summary = "comment 생성")
    @PostMapping
    fun createComment(
//        @PathVariable articleId: Long,
        @RequestBody createCommentRequest: CreateCommentRequest,
    ) : ResponseEntity<CommentResponse> {

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(createCommentRequest))
    }

    @Operation(summary = "comment 수정")
    @PutMapping("/{commentId}")
    fun updateComment(
        @RequestBody updateCommentRequest: UpdateCommentRequest
    ) : ResponseEntity<CommentResponse> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(updateCommentRequest))
    }

    @Operation(summary = "comment 삭제")
    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @RequestBody deleteCommentRequest: DeleteCommentRequest
    ): HttpStatus {
        commentService.deleteComment(deleteCommentRequest)

        return HttpStatus.OK
    }




}