package com.teamsparta.newsfeed.domain.exception

import com.teamsparta.newsfeed.domain.exception.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(ArticleNotFoundException::class)
    fun handleArticleNotFoundException(e: ArticleNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(MemberNotFoundException::class)
    fun memberNotFoundException(e: MemberNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(InvalidCredentialException::class)
    fun handleInvalidCredentialException(e: InvalidCredentialException): ResponseEntity<ErrorResponse> {
        //400 Bad Request
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse(message = e.message))
    }


}