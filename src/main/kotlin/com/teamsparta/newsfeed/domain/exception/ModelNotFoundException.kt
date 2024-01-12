package com.teamsparta.newsfeed.domain.exception

data class ModelNotFoundException(var model: String, var memberId: Long) :
    RuntimeException("$model not found with given id: $memberId")
