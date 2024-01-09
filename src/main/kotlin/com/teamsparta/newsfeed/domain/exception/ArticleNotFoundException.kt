package com.teamsparta.newsfeed.domain.exception

data class ArticleNotFoundException(val articleTitle: String, val id: Long?) :
        RuntimeException("$articleTitle not found with given id: $id")